
import com.snowcat.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            dependencies {
                add("testImplementation", libs.findLibrary("androidx.test.core").get())
                add("testImplementation", libs.findLibrary("androidx.test.ext").get())
                add("testImplementation", libs.findLibrary("androidx.test.rules").get())
                add("testImplementation", libs.findLibrary("androidx.test.runner").get())
                add("testImplementation", libs.findLibrary("espresso.core").get())
                add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
                add("testImplementation", libs.findLibrary("mockk-android").get())
                add("testImplementation", libs.findLibrary("mockk-agent").get())
            }
        }
    }
}