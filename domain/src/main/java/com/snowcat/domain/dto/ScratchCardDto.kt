package com.snowcat.domain.dto

/**
 * Data transfer object representing a scratch card.
 */
data class ScratchCardDto(
    val id: Int = 0,
    val value: String? = null,
    val scratchState: ScratchStateDto = ScratchStateDto.NOT_SCRATCHED,
    val valid: ValidationStateDto = ValidationStateDto.NOT_VALIDATED
)

/**
 * Enum representing the scratch state of a scratch card.
 */
enum class ScratchStateDto {
    SCRATCHED,
    NOT_SCRATCHED
}

/**
 * Enum representing the validation state of a scratch card.
 */
enum class ValidationStateDto {
    VALID,
    INVALID,
    NOT_VALIDATED
}
