plugins {
    alias(libs.plugins.snwct.android.library)
    alias(libs.plugins.snwct.android.room)
    alias(libs.plugins.snwct.android.hilt)
}

// Force specific versions of serialization libraries to avoid conflicts between room and KSP
configurations.all {
    resolutionStrategy.eachDependency {
        val serdeVer = "1.9.0" // should be >= 1.8.0
        when(requested.module.toString()) {
            // json serialization
            "org.jetbrains.kotlinx:kotlinx-serialization-json" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-json-jvm" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-core" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-core-jvm" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-bom" -> useVersion(serdeVer)
        }
    }
}

android {
    namespace = "com.syngenta.ppm.database"
}