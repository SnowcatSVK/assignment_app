package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.database.entity.ScratchState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import java.util.UUID
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

/**
 * Use case for scratching a scratch card.
 */
class ScratchCardUseCase @Inject constructor(
    private val scratchCardDataSource: ScratchCardDataSource
) {

    /**
     * Scratches the scratch card by updating its scratch state to SCRATCHED
     * and assigning a random UUID as its value after a delay.
     */
    suspend fun scratchCard() {
        val scratchCard = scratchCardDataSource.getScratchCard().first()
            ?: error("ScratchCard not found")
        val updatedScratchCard =
            scratchCard.copy(
                scratchState = ScratchState.SCRATCHED,
                value = UUID.randomUUID().toString()
            )
        delay(2.seconds)
        scratchCardDataSource.insertOrUpdateScratchCard(updatedScratchCard)
    }
}
