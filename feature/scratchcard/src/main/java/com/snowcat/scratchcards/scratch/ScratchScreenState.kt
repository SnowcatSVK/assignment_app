package com.snowcat.scratchcards.scratch

import com.snowcat.designsystem.components.ButtonState

/**
 * State for the Scratch Screen.
 */
data class ScratchScreenState(
    val code: String = "",
    val isScratched: Boolean = false,
    val buttonState: ButtonState = ButtonState.ENABLED
)
