package com.snowcat.network

import com.snowcat.assignment.network.BuildConfig
import com.snowcat.network.scratch.ScratchApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject

/**
 * Api Client to provide Retrofit instances.
 */
class ApiClient @Inject constructor(
    private val loggingInterceptor: HttpLoggingInterceptor,
    private val networkJson: Json
) {

    /**
     * Get default api.
     */
    fun getScratchApi(): ScratchApi = getUnauthenticatedRetrofit().create(ScratchApi::class.java)

    /**
     * Get unauthenticated retrofit instance.
     */
    private fun getUnauthenticatedRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl) // Replace with your base URL
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
    }
}
