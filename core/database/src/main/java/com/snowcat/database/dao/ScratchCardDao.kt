package com.snowcat.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.snowcat.database.entity.ScratchCardEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for scratch card entities.
 */
@Dao
interface ScratchCardDao {

    /**
     * Insert or update a scratch card entity.
     */
    @Upsert
    suspend fun upsertScratchCard(scratchCardEntity: ScratchCardEntity)

    /**
     * Get all scratch card entities.
     */
    @Query("SELECT * FROM ${ScratchCardEntity.TABLE_NAME}")
    fun getAllScratchCards(): Flow<List<ScratchCardEntity>>

    /**
     * Get a scratch card entity by its ID.
     */
    @Query("SELECT * FROM ${ScratchCardEntity.TABLE_NAME} WHERE ${ScratchCardEntity.ID} = :id")
    fun getScratchCard(id: Int = 0): Flow<ScratchCardEntity?>
}
