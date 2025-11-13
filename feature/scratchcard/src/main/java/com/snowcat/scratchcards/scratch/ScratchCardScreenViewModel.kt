package com.snowcat.scratchcards.scratch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snowcat.designsystem.components.ButtonState
import com.snowcat.domain.GetScratchCardUseCase
import com.snowcat.domain.ScratchCardUseCase
import com.snowcat.domain.dto.ScratchStateDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Scratch Card Screen.
 */
@HiltViewModel
class ScratchCardScreenViewModel @Inject constructor(
    private val getScratchCardUseCase: GetScratchCardUseCase,
    private val scratchCardUseCase: ScratchCardUseCase
) : ViewModel() {

    var state by mutableStateOf(ScratchScreenState())
        private set

    init {
        viewModelScope.launch {
            getScratchCardUseCase.getScratchCard().collect { card ->
                card?.let {
                    state = state.copy(
                        code = it.value.orEmpty(),
                        isScratched = it.scratchState == ScratchStateDto.SCRATCHED,
                        buttonState = when (it.scratchState) {
                            ScratchStateDto.NOT_SCRATCHED -> ButtonState.ENABLED
                            ScratchStateDto.SCRATCHED -> ButtonState.SUCCESS
                        }
                    )
                }
            }
        }
    }

    /**
     * Scratch the scratch card.
     */
    fun scratchCard() {
        viewModelScope.launch {
            state = state.copy(buttonState = ButtonState.LOADING)
            scratchCardUseCase.scratchCard()
        }
    }
}
