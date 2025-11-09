package com.snowcat.build_logicimport extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidRetrofitConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            dependencies.add("implementation", libs.findLibrary("okhttp.logging").get())
            dependencies.add("implementation", libs.findLibrary("retrofit.core").get())
            dependencies.add(
                "implementation",
                libs.findLibrary("retrofit.kotlin.serialization").get()
            )
            dependencies.add(
                "implementation",
                libs.findLibrary("kotlinx.serialization.json").get()
            )
            dependencies.add(
                "implementation",
                libs.findLibrary("retrofit.scalars").get()
            )
            dependencies.add("implementation", libs.findLibrary("coil.kt").get())
            dependencies.add("implementation", libs.findLibrary("coil.kt-svg").get())
        }
    }
}