package com.snowcat.domain.mapper

import com.snowcat.database.entity.ScratchCardEntity
import com.snowcat.database.entity.ScratchState
import com.snowcat.database.entity.ValidationState
import com.snowcat.domain.dto.ScratchCardDto
import com.snowcat.domain.dto.ScratchStateDto
import com.snowcat.domain.dto.ValidationStateDto

/**
 * Maps a [ScratchCardEntity] to a [ScratchCardDto].
 */
fun ScratchCardEntity.toDto() = ScratchCardDto(
    id = id,
    value = value,
    scratchState = scratchState.toScratchStateDto(),
    valid = valid.toValidationStateDto()
)

/**
 * Maps a [ScratchState] to a [ScratchStateDto].
 */
fun ScratchState.toScratchStateDto(): ScratchStateDto {
    return when (this) {
        ScratchState.SCRATCHED -> ScratchStateDto.SCRATCHED
        ScratchState.NOT_SCRATCHED -> ScratchStateDto.NOT_SCRATCHED
    }
}

/**
 * Maps a [ValidationState] to a [ValidationStateDto].
 */
fun ValidationState.toValidationStateDto(): ValidationStateDto {
    return when (this) {
        ValidationState.VALID -> ValidationStateDto.VALID
        ValidationState.INVALID -> ValidationStateDto.INVALID
        ValidationState.NOT_VALIDATED -> ValidationStateDto.NOT_VALIDATED
    }
}
