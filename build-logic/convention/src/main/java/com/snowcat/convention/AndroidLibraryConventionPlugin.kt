package com.snowcat.build_logicimport com.android.build.gradle.LibraryExtension
import extensions.PPMFlavor
import extensions.configureFlavors
import extensions.configureKotlinAndroid
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.util.Properties

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("ppm.android.detekt")
                apply("ppm.android.lint")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                configureFlavors(this) {
                    val file = rootProject.file("keys.properties")
                    val props = Properties()
                    props.load(file.inputStream())
                    buildConfigField("String", "GOOGLE_API", props.getProperty("GOOGLE_PLACE"))
                    buildConfigField("String", "GSSO_KEY", props.getProperty("SGN_KEY"))
                    manifestPlaceholders["MAPS_API_KEY"] = props.getProperty("MAPS_API_KEY")
                    when (it) {
                        PPMFlavor.dev -> {
                            signingConfigs {
                                create("dsm") {
                                    storeFile = rootProject.file("dsm-keystore.jks")
                                    storePassword = props.getProperty("DSM_PSSWD")
                                    keyAlias = "dsm_ppm_key"
                                    keyPassword = props.getProperty("DSM_PSSWD")
                                }
                            }
                        }

                        PPMFlavor.stage, PPMFlavor.prod -> {
                            signingConfigs {
                                maybeCreate("syngenta").apply {
                                    storeFile = rootProject.file("ppm-keystore.jks")
                                    storePassword = props.getProperty("PPM_PSSWD")
                                    keyAlias = "syngenta_ppm_key"
                                    keyPassword = props.getProperty("PPM_PSSWD")
                                }
                            }
                        }
                    }

                    buildTypes {
                        getByName("release") {
                            isMinifyEnabled = false
                            isShrinkResources = false
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                            productFlavors.getByName(it.name).signingConfig = when (it) {
                                PPMFlavor.dev -> signingConfigs.getByName("dsm")
                                else -> signingConfigs.getByName("syngenta")
                            }
                        }

                        getByName("debug") {
                            isMinifyEnabled = false
                            signingConfig = null
                            productFlavors.getByName(it.name).signingConfig = when (it) {
                                PPMFlavor.dev -> signingConfigs.getByName("dsm")
                                else -> signingConfigs.getByName("syngenta")
                            }
                        }
                    }
                }
                compileOptions.sourceCompatibility = JavaVersion.VERSION_17
                compileOptions.targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }
}
