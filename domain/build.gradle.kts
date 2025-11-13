plugins {
    alias(libs.plugins.snwct.android.library)
    alias(libs.plugins.snwct.android.hilt)
    alias(libs.plugins.snwct.android.test)
}

android {
    namespace = "com.snowcat.assignment.domain"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
}