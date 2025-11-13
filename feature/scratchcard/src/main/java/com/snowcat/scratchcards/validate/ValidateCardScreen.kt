package com.snowcat.scratchcards.validate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.snowcat.designsystem.Spacing
import com.snowcat.designsystem.components.AnimatedButton
import com.snowcat.designsystem.components.DEFAULT_CARD_HEIGHT_PERCENTAGE
import com.snowcat.designsystem.components.ScratchCard
import com.snowcat.scratchcard.R
import com.snowcat.scratchcards.navigation.ScratchGraph

/**
 * Validate Card Screen.
 */
@Destination<ScratchGraph>
@Composable
fun ValidateCardScreen(
    viewModel: ValidateCardViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Spacing.grid6),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScratchCard(
            modifier = Modifier.fillMaxHeight(DEFAULT_CARD_HEIGHT_PERCENTAGE),
            code = state.code,
            scratched = true
        )
        Spacer(Modifier.weight(1f))
        AnimatedButton(
            text = stringResource(R.string.button_validate_card),
            state = state.buttonState
        ) {
            viewModel.validateCard()
        }
    }
}
