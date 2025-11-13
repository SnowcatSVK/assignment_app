package com.snowcat.database.di

import android.content.Context
import androidx.room.Room
import com.snowcat.database.ScratchDatabase
import com.snowcat.database.ScratchDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module to provide database instance.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the ScratchDatabase instance.
     */
    @Provides
    @Singleton
    @Suppress("SpreadOperator")
    fun providePPMDatabase(@ApplicationContext context: Context): ScratchDatabase =
        Room.databaseBuilder(
            context,
            ScratchDatabase::class.java,
            DATABASE_NAME
        ).build()
}

/**
 * Hilt module to provide DAO instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    /**
     * Provides the [com.snowcat.database.dao.ScratchCardDao] instance.
     */
    @Provides
    @Singleton
    fun provideScratchDao(database: ScratchDatabase) = database.scratchDao()
}
