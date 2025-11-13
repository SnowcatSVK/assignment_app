package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.database.entity.ScratchCardEntity
import com.snowcat.domain.mapper.toDto
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetScratchCardUseCaseTest {
    private lateinit var dataSource: ScratchCardDataSource
    private lateinit var useCase: GetScratchCardUseCase

    @Before
    fun setup() {
        dataSource = mockk(relaxed = true)
        useCase = GetScratchCardUseCase(dataSource)
    }

    @Test
    fun `getScratchCard returns mapped DTO`() = runTest {
        val entity = ScratchCardEntity()
        every { dataSource.getScratchCard() } returns flowOf(entity)
        val result = useCase.getScratchCard()
        result.collect { dto ->
            assertEquals(entity.toDto(), dto)
        }
    }
}
