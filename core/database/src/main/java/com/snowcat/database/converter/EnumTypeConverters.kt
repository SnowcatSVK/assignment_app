package com.snowcat.database.converter

import androidx.room.TypeConverter
import com.snowcat.database.entity.ScratchState
import com.snowcat.database.entity.ValidationState

/**
 * Enum type converters for Room database.
 */
class EnumTypeConverters {
    /**
     * Convert location to string.
     */
    @TypeConverter
    fun fromScratchState(scratchState: ScratchState): String = scratchState.name

    /**
     * Convert string to location.
     */
    @TypeConverter
    fun toScratchState(scratchState: String): ScratchState = ScratchState.valueOf(scratchState)

    /**
     * Convert validation state to string.
     */
    @TypeConverter
    fun fromValidationState(validationState: ValidationState): String = validationState.name

    /**
     * Convert string to validation state.
     */
    @TypeConverter
    fun toValidationState(validationState: String): ValidationState =
        ValidationState.valueOf(validationState)
}
