
import com.android.build.gradle.LibraryExtension
import com.snowcat.convention.extensions.configureFlavors
import com.snowcat.convention.extensions.configureKotlinAndroid
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
                apply("snwct.android.detekt")
                apply("snwct.android.lint")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 36
                configureFlavors(this)
                compileOptions.sourceCompatibility = JavaVersion.VERSION_18
                compileOptions.targetCompatibility = JavaVersion.VERSION_18
            }
        }
    }
}
