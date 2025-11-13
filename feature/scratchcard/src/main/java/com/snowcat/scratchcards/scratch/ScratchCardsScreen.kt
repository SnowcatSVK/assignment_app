package com.snowcat.scratchcards.scratch

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
 * Scratch Card Screen.
 */
@Destination<ScratchGraph>(start = true)
@Composable
fun ScratchCardScreen(
    viewModel: ScratchCardScreenViewModel = hiltViewModel()
) {
    val scratchCardState = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Spacing.grid6),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScratchCard(
            modifier = Modifier.fillMaxHeight(DEFAULT_CARD_HEIGHT_PERCENTAGE),
            code = scratchCardState.code,
            scratched = scratchCardState.isScratched
        )
        Spacer(Modifier.weight(1f))
        AnimatedButton(
            text = stringResource(R.string.button_scratch_card),
            state = scratchCardState.buttonState
        ) {
            viewModel.scratchCard()
        }
    }
}
