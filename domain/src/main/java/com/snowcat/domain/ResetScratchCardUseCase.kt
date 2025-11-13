package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.database.entity.ScratchCardEntity
import com.snowcat.database.entity.ScratchState
import com.snowcat.database.entity.ValidationState
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Use case for resetting a scratch card to its initial state.
 */
class ResetScratchCardUseCase @Inject constructor(
    private val scratchCardDataSource: ScratchCardDataSource
) {

    /**
     * Resets the scratch card by setting its scratch state to NOT_SCRATCHED,
     * value to null, and validation state to NOT_VALIDATED.
     */
    suspend fun resetScratchCard() {
        val resetScratchCard = scratchCardDataSource
            .getScratchCard()
            .first()
            ?.copy(
                scratchState = ScratchState.NOT_SCRATCHED,
                value = null,
                valid = ValidationState.NOT_VALIDATED
            ) ?: ScratchCardEntity()
        scratchCardDataSource.insertOrUpdateScratchCard(resetScratchCard)
    }
}
