package com.snowcat.network.di

import com.snowcat.network.ApiClient
import com.snowcat.network.scratch.ScratchClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InterceptorSetup {
    /**
     * Provides http logging interceptor.
     */
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
}

/**
 * Remote setup module.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object RemoteSetup {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
    }

    /**
     * Provides api client.
     */
    @Provides
    @Singleton
    fun provideApiClient(
        networkJson: Json,
        loggingInterceptor: HttpLoggingInterceptor
    ): ApiClient = ApiClient(
        loggingInterceptor,
        networkJson
    )
}

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    fun provideIdentityClient(
        apiClient: ApiClient,
    ): ScratchClient = ScratchClient(apiClient)
}
