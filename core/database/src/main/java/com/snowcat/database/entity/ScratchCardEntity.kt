package com.snowcat.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a scratch card in the database.
 */
@Entity(tableName = ScratchCardEntity.TABLE_NAME)
data class ScratchCardEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int = 0,
    @ColumnInfo(name = VALUE)
    val value: String? = null,
    @ColumnInfo(name = SCRATCH_STATE)
    val scratchState: ScratchState = ScratchState.NOT_SCRATCHED,
    @ColumnInfo(name = VALIDATION_STATE)
    val valid: ValidationState = ValidationState.NOT_VALIDATED
) {

    /** Table and column name constants. */
    companion object {
        const val TABLE_NAME = "scratch_card_table"
        const val ID = "id"
        const val VALUE = "value"
        const val SCRATCH_STATE = "scratch_state"
        const val VALIDATION_STATE = "validation_state"
    }
}

/**
 * Enum representing the scratch state of a scratch card.
 */
enum class ScratchState {
    SCRATCHED,
    NOT_SCRATCHED
}

/**
 * Enum representing the validation state of a scratch card.
 */
enum class ValidationState {
    VALID,
    INVALID,
    NOT_VALIDATED
}
