package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.database.entity.ScratchCardEntity
import com.snowcat.database.entity.ScratchState
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ScratchCardUseCaseTest {
    private lateinit var dataSource: ScratchCardDataSource
    private lateinit var useCase: ScratchCardUseCase

    @Before
    fun setup() {
        dataSource = mockk(relaxed = true)
        useCase = ScratchCardUseCase(dataSource)
    }

    @Test
    fun `scratchCard updates state and value`() = runTest {
        val entity = ScratchCardEntity(scratchState = ScratchState.NOT_SCRATCHED)
        every { dataSource.getScratchCard() } returns flowOf(entity)
        coJustRun { dataSource.insertOrUpdateScratchCard(any()) }

        useCase.scratchCard()

        coVerify {
            dataSource.insertOrUpdateScratchCard(
                match {
                    it.scratchState == ScratchState.SCRATCHED && it.value != null
                }
            )
        }
    }
}
