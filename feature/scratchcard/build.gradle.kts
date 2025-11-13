plugins {
    alias(libs.plugins.snwct.android.feature)
    alias(libs.plugins.snwct.android.library.compose)
}

android {
    namespace = "com.snowcat.scratchcard"

    ksp {
        arg("compose-destinations.moduleName", "scratchCard")
        arg("compose-destinations.htmlMermaidGraph", "$rootDir//navigation-docs")
    }
}

dependencies {
    implementation(project(":core:designsystem"))
}