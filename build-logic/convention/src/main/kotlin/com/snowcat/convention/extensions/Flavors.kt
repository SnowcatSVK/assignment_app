package com.snowcat.convention.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    baseUrl
}

@Suppress("EnumEntryName")
enum class Flavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    dev(FlavorDimension.baseUrl, applicationIdSuffix = ".dev"),
    prod(FlavorDimension.baseUrl)
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: Flavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.baseUrl.name
        productFlavors {
            Flavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    // if baseUrl changes, please also change in
                    // - AndroidApplicationFlavorsConventionPlugin.apply
                    // - AndroidLibraryConventionPlugin.apply
                    val baseUrl = "https://api.o2.sk/"
                    buildConfigField(
                        "String",
                        FlavorDimension.baseUrl.name,
                        "\"$baseUrl\""
                    )
                    buildConfigField("String", "applicationId", "\"com.snowcat.assignmentapp\"")

                    flavorConfigurationBlock(this, it)

                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        applicationId = "com.snowcat.assignmentapp"
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
