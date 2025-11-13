package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.database.entity.ValidationState
import com.snowcat.network.scratch.ScratchClient
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

/**
 * Use case for validating a scratch card.
 */
class ValidateCardUseCase @Inject constructor(
    private val client: ScratchClient,
    private val dataSource: ScratchCardDataSource
) {

    /**
     * Validates the scratch card by verifying its code with the ScratchClient.
     * Updates the scratch card's validation state in the database.
     *
     * @return True if the scratch card is valid, false if invalid, or null if an error occurred.
     */
    suspend fun validateCard(): Boolean? {
        return try {
            val scratchCard = dataSource.getScratchCard().first() ?: error("ScratchCard not found")
            val isValid = client.verifyScratchCode(scratchCard.value ?: "").android.isValidScratchCode()
            val updatedScratchCard = scratchCard.copy(valid = isValid)
            dataSource.insertOrUpdateScratchCard(updatedScratchCard)
            isValid == ValidationState.VALID
        } catch (e: Exception) {
            Timber.e(e, "Error validating scratch card")
            null
        }
    }

    private fun String.isValidScratchCode(): ValidationState {
        val value = this.toIntOrNull()
        return when {
            value != null && value > VALID_SCRATCH_CODE_THRESHOLD -> ValidationState.VALID
            else -> ValidationState.INVALID
        }
    }
}

const val VALID_SCRATCH_CODE_THRESHOLD = 277028
