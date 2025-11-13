package com.snowcat.database.datasource

import com.snowcat.database.dao.ScratchCardDao
import com.snowcat.database.entity.ScratchCardEntity
import javax.inject.Inject

/**
 * Data source for scratch card operations.
 */
class ScratchCardDataSource @Inject constructor(
    private val scratchCardDao: ScratchCardDao
) {

    /**
     * Insert or update a scratch card entity.
     */
    suspend fun insertOrUpdateScratchCard(scratchCardEntity: ScratchCardEntity) =
        scratchCardDao.upsertScratchCard(scratchCardEntity)

    /**
     * Get a scratch card entity by its ID.
     */
    fun getScratchCard() = scratchCardDao.getScratchCard()
}
