package com.snowcat.domain

import com.snowcat.database.datasource.ScratchCardDataSource
import com.snowcat.domain.mapper.toDto
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case for retrieving a scratch card.
 */
class GetScratchCardUseCase @Inject constructor(
    private val dataSource: ScratchCardDataSource
) {

    /**
     * Retrieves the scratch card from the data source and maps it to a DTO.
     */
    fun getScratchCard() = dataSource.getScratchCard().map { it?.toDto() }
}
