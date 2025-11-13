package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.database.entity.ScratchCardEntity
import com.snowcat.database.entity.ValidationState
import com.snowcat.network.scratch.ScratchClient
import com.snowcat.network.scratch.response.VerifyScratchResponse
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidateCardUseCaseTest {
    private lateinit var client: ScratchClient
    private lateinit var dataSource: ScratchCardDataSource
    private lateinit var useCase: ValidateCardUseCase

    @Before
    fun setup() {
        client = mockk(relaxed = true)
        dataSource = mockk(relaxed = true)
        useCase = ValidateCardUseCase(client, dataSource)
    }

    @Test
    fun `validateCard returns true for valid code`() = runTest {
        val entity = ScratchCardEntity(value = "277029")
        every { dataSource.getScratchCard() } returns flowOf(entity)
        coEvery { client.verifyScratchCode(any()) } returns VerifyScratchResponse("277029")
        coJustRun { dataSource.insertOrUpdateScratchCard(any()) }

        val result = useCase.validateCard()
        assertTrue(result == true)
        coVerify {
            dataSource.insertOrUpdateScratchCard(
                match { it.valid == ValidationState.VALID }
            )
        }
    }

    @Test
    fun `validateCard returns null on exception`() = runTest {
        every { dataSource.getScratchCard() } throws RuntimeException("error")
        val result = useCase.validateCard()
        assertNull(result)
    }
}
