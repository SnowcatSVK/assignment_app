import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.room.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
}

//gradlePlugin {
//    plugins {
//        register("androidApplicationCompose") {
//            id = "ppm.android.application.compose"
//            implementationClass = "AndroidApplicationComposeConventionPlugin"
//        }
//        register("androidApplication") {
//            id = "ppm.android.application"
//            implementationClass = "AndroidApplicationConventionPlugin"
//        }
//        register("androidApplicationFirebase") {
//            id = "ppm.android.application.firebase"
//            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
//        }
//        register("androidApplicationFlavors") {
//            id = "ppm.android.application.flavors"
//            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
//        }
//        register("androidFeature") {
//            id = "ppm.android.feature"
//            implementationClass = "AndroidFeatureConventionPlugin"
//        }
//        register("androidHilt") {
//            id = "ppm.android.hilt"
//            implementationClass = "AndroidHiltConventionPlugin"
//        }
//        register("androidLibraryCompose") {
//            id = "ppm.android.library.compose"
//            implementationClass = "AndroidLibraryComposeConventionPlugin"
//        }
//        register("androidLibrary") {
//            id = "ppm.android.library"
//            implementationClass = "AndroidLibraryConventionPlugin"
//        }
//        register("androidLint") {
//            id = "ppm.android.lint"
//            implementationClass = "AndroidLintConventionPlugin"
//        }
//        register("androidRoom") {
//            id = "ppm.android.room"
//            implementationClass = "AndroidRoomConventionPlugin"
//        }
//        register("androidRetrofit") {
//            id = "ppm.android.retrofit"
//            implementationClass = "AndroidRetrofitConventionPlugin"
//        }
//        register("androidDetekt") {
//            id = "ppm.android.detekt"
//            implementationClass = "AndroidDetektConventionPlugin"
//        }
//    }
//}