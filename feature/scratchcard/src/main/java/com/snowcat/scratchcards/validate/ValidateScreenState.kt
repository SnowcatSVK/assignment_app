package com.snowcat.scratchcards.validate

import com.snowcat.designsystem.components.ButtonState

/**
 * State for the Validate Screen.
 */
data class ValidateScreenState(
    val code: String = "",
    val isValid: Boolean? = null,
    val buttonState: ButtonState = ButtonState.ENABLED
)
