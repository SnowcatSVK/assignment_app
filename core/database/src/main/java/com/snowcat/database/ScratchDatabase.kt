package com.snowcat.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.snowcat.database.converter.EnumTypeConverters
import com.snowcat.database.dao.ScratchCardDao
import com.snowcat.database.entity.ScratchCardEntity

/**
 * Room database for scratch card entities.
 */
@Database(
    entities = [ScratchCardEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(EnumTypeConverters::class)
abstract class ScratchDatabase : RoomDatabase() {

    /**
     * Provides the DAO for scratch card operations.
     */
    abstract fun scratchDao(): ScratchCardDao

    /** Database name constant. */
    companion object {
        const val DATABASE_NAME = "scratch_database"
    }
}
