// If the project has build-logic module with convention module. (for example: https://github.com/android/nowinandroid/tree/main/build-logic)
//gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Assignment App"
include(":app")
include(":core")
include(":core:network")
include(":core:database")
include(":domain")
include(":feature:scratchcard")
include(":core:designsystem")
