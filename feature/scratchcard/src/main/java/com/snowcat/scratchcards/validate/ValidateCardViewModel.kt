package com.snowcat.scratchcards.validate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snowcat.designsystem.components.ButtonState
import com.snowcat.domain.GetScratchCardUseCase
import com.snowcat.domain.ValidateCardUseCase
import com.snowcat.domain.dto.ValidationStateDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Validate Card Screen.
 */
@HiltViewModel
class ValidateCardViewModel @Inject constructor(
    private val getScratchCardUseCase: GetScratchCardUseCase,
    private val validateCardUseCase: ValidateCardUseCase
) : ViewModel() {

    var state by mutableStateOf(ValidateScreenState())
        private set

    init {
        viewModelScope.launch {
            getScratchCardUseCase.getScratchCard().collect { card ->
                card?.let {
                    state = state.copy(
                        code = it.value.orEmpty(),
                        isValid = card.valid == ValidationStateDto.VALID,
                        buttonState = getButtonState(card.valid)
                    )
                }
            }
        }
    }

    /**
     * Validate the scratch card.
     */
    fun validateCard() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            state = state.copy(buttonState = ButtonState.LOADING)
            val isValid = validateCardUseCase.validateCard()
            state = state.copy(
                isValid = isValid,
                buttonState = when (isValid) {
                    true -> ButtonState.SUCCESS
                    false -> ButtonState.FAILURE
                    null -> ButtonState.ERROR
                }
            )
        }
    }

    private fun getButtonState(validationState: ValidationStateDto): ButtonState {
        return when (validationState) {
            ValidationStateDto.NOT_VALIDATED -> ButtonState.ENABLED
            ValidationStateDto.VALID -> ButtonState.SUCCESS
            ValidationStateDto.INVALID -> ButtonState.FAILURE
        }
    }
}
