package com.snowcat.assignmentapp.home

import com.snowcat.designsystem.components.ButtonState

/**
 * UI state for the Home Screen.
 */
data class HomeScreenState(
    val code: String = "",
    val isScratched: Boolean = false,
    val scratchButtonState: ButtonState = ButtonState.ENABLED,
    val validateButtonState: ButtonState = ButtonState.DISABLED
)
