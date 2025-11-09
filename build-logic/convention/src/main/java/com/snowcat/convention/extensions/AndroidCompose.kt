package extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("kotlin").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("androidx-ui").get())
            add("implementation", libs.findLibrary("androidx-material3").get())
            add("implementation", libs.findLibrary("androidx-ui-graphics").get())
            add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())
            add("implementation", libs.findLibrary("androidx-ui-tooling").get())
            add("implementation", libs.findLibrary("androidx-constraint-layout").get())
            add("implementation", libs.findLibrary("coil-kt-compose").get())
            add("implementation", libs.findLibrary("coil-kt-svg").get())
            add("implementation", libs.findLibrary("raamcosta-destinations").get())
            add("ksp", libs.findLibrary("raamcosta-destinations-ksp").get())
            add("implementation", libs.findLibrary("hilt-ext-navigation-compose").get())
        }
    }
}