package com.snowcat.network.scratch.response

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

/**
 * Verify Scratch Response.
 *
 * @param android the android scratch verification string.
 */
@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VerifyScratchResponse(
    val android: String
)
