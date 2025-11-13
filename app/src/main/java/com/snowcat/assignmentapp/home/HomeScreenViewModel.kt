package com.snowcat.assignmentapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snowcat.designsystem.components.ButtonState
import com.snowcat.domain.GetScratchCardUseCase
import com.snowcat.domain.dto.ScratchStateDto
import com.snowcat.domain.dto.ValidationStateDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Home Screen.
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    getScratchCardUseCase: GetScratchCardUseCase
) : ViewModel() {

    /**
     * UI state of the Home Screen.
     */
    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        viewModelScope.launch {
            getScratchCardUseCase.getScratchCard().collect { card ->
                card?.let {
                    state = state.copy(
                        code = it.value.orEmpty(),
                        isScratched = it.scratchState == ScratchStateDto.SCRATCHED,
                        scratchButtonState = getScratchButtonState(it.scratchState),
                        validateButtonState = getValidateButtonState(it.scratchState, it.valid)
                    )
                }
            }
        }
    }

    private fun getScratchButtonState(scratchCardState: ScratchStateDto): ButtonState {
        return when (scratchCardState) {
            ScratchStateDto.NOT_SCRATCHED -> ButtonState.ENABLED
            ScratchStateDto.SCRATCHED -> ButtonState.DISABLED
        }
    }

    private fun getValidateButtonState(
        scratchCardState: ScratchStateDto,
        validationStateDto: ValidationStateDto
    ): ButtonState {
        return if (scratchCardState == ScratchStateDto.SCRATCHED &&
            validationStateDto == ValidationStateDto.NOT_VALIDATED
        ) {
            ButtonState.ENABLED
        } else {
            ButtonState.DISABLED
        }
    }
}
