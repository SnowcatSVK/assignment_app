package com.snowcat.build_logicimport

import com.android.build.api.dsl.ApplicationExtension
import extensions.PPMFlavor
import extensions.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.util.Properties

class AndroidApplicationFlavorsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
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
                            applicationIdSuffix = null
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
                            applicationIdSuffix = null
                            isMinifyEnabled = false
                            signingConfig = null
                            productFlavors.getByName(it.name).signingConfig = when (it) {
                                PPMFlavor.dev -> signingConfigs.getByName("dsm")
                                else -> signingConfigs.getByName("syngenta")
                            }
                        }
                    }
                }
            }
        }
    }
}