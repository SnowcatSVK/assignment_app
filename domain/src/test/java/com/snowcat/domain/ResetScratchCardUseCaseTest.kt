package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.database.entity.ScratchCardEntity
import com.snowcat.database.entity.ScratchState
import com.snowcat.database.entity.ValidationState
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ResetScratchCardUseCaseTest {
    private lateinit var dataSource: ScratchCardDataSource
    private lateinit var useCase: ResetScratchCardUseCase

    @Before
    fun setup() {
        dataSource = mockk(relaxed = true)
        useCase = ResetScratchCardUseCase(dataSource)
    }

    @Test
    fun `resetScratchCard resets card state`() = runTest {
        val entity = ScratchCardEntity(
            scratchState = ScratchState.SCRATCHED,
            value = "123",
            valid = ValidationState.VALID
        )
        every { dataSource.getScratchCard() } returns flowOf(entity)
        coJustRun { dataSource.insertOrUpdateScratchCard(any()) }

        useCase.resetScratchCard()

        coVerify {
            dataSource.insertOrUpdateScratchCard(
                match {
                    it.scratchState == ScratchState.NOT_SCRATCHED &&
                    it.value == null &&
                    it.valid == ValidationState.NOT_VALIDATED
                }
            )
        }
    }
}
