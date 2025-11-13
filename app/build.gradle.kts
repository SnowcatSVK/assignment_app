plugins {
    alias(libs.plugins.snwct.android.application)
    alias(libs.plugins.snwct.android.hilt)
    alias(libs.plugins.snwct.android.application.compose)
    alias(libs.plugins.snwct.android.application.flavors)
    alias(libs.plugins.snwct.android.lint)
    id("kotlin-parcelize")
}

android {
    namespace = "com.snowcat.assignmentapp"

    defaultConfig {
        applicationId = "com.snowcat.assignmentapp"
        versionCode = 36
        versionName = "2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    ksp {
        arg("compose-destinations.moduleName", "app")
        arg("compose-destinations.htmlMermaidGraph", "$rootDir//navigation-docs")
    }

    dependencies {
        implementation(project(":core:designsystem"))
        implementation(project(":domain"))
        implementation(project(":feature:scratchcard"))
    }
}