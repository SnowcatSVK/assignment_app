package com.snowcat.network.scratch

import com.snowcat.network.ApiClient
import javax.inject.Inject

/**
 * Client for Scratch API interactions.
 */
class ScratchClient @Inject constructor(
    private val apiClient: ApiClient
) {

    // Lazy initialization of ScratchApi
    private val scratchApi: ScratchApi by lazy {
        apiClient.getScratchApi()
    }

    /**
     * Verify a scratch code.
     *
     * @param code the scratch code to verify.
     * @return the verification response.
     */
    suspend fun verifyScratchCode(code: String) =
        scratchApi.verifyScratch(code)
}
