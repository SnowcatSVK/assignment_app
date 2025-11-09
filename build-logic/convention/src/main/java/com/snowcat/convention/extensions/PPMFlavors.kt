package extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    baseUrl,
    externalFacebookUrl,
    externalSyngentaUrl
}


@Suppress("EnumEntryName")
enum class PPMFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    dev(FlavorDimension.baseUrl, applicationIdSuffix = ".dev"),
    stage(FlavorDimension.baseUrl),
    prod(FlavorDimension.baseUrl)
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: PPMFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.baseUrl.name
        productFlavors {
            PPMFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    // if baseUrl changes, please also change in
                    // - AndroidApplicationFlavorsConventionPlugin.apply
                    // - AndroidLibraryConventionPlugin.apply
                    val baseUrl = when (it) {
                        PPMFlavor.dev -> "https://ppm.dev.dsminds.eu/api/"
                        PPMFlavor.stage -> "https://stage.ppm.syngentadigitalapps.com/api/"
                        PPMFlavor.prod -> "https://ppm.dsminds.eu/api/"
                    }
                    val cdnBaseUrl = when(it) {
                        PPMFlavor.dev -> "https://cdn.ppm.dev.dsminds.eu/"
                        PPMFlavor.stage -> "https://cdn.stage.ppm.syngentadigitalapps.com/"
                        PPMFlavor.prod -> "https://cdn.ppm.dsminds.eu/"
                    }
                    val deeplinkServerAddress = when (it) {
                        PPMFlavor.dev -> "ppm.dev.dsminds.eu"
                        PPMFlavor.stage -> "stage.ppm.syngentadigitalapps.com"
                        PPMFlavor.prod -> "ppm.dsminds.eu"
                    }
                    buildConfigField("String", FlavorDimension.baseUrl.name, "\"$baseUrl\"")
                    buildConfigField("String", "cdnBaseUrl", "\"${cdnBaseUrl}\"")
                    buildConfigField(
                        "String",
                        FlavorDimension.externalFacebookUrl.name,
                        "\"${baseUrl}identity/auth/login/external?provider=facebook\""
                    )
                    buildConfigField(
                        "String",
                        FlavorDimension.externalSyngentaUrl.name,
                        "\"${baseUrl}identity/auth/login/external?provider=syngenta\""
                    )
                    buildConfigField("String", "applicationId", "\"com.syngenta.ppm\"")

                    manifestPlaceholders["DEEPLINK_SERVER"] = deeplinkServerAddress
                    buildConfigField(
                        "String",
                        "DEEPLINK_SERVER",
                        "\"$deeplinkServerAddress\""
                    )

                    flavorConfigurationBlock(this, it)

                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        applicationId = "com.syngenta.ppm_na"
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
