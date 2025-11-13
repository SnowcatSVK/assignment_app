package com.snowcat.network.scratch

import com.snowcat.network.scratch.response.VerifyScratchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Scratch API.
 */
interface ScratchApi {

    /**
     * Verify Scratch Code.
     *
     * @param code the scratch code to verify.
     * @return the verify scratch response.
     */
    @GET("version/")
    suspend fun verifyScratch(
        @Query("code") code: String
    ): VerifyScratchResponse
}
