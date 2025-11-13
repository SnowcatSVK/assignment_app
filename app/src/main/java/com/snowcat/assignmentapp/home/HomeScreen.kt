package com.snowcat.assignmentapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.generated.scratchcard.destinations.ScratchCardScreenDestination
import com.ramcosta.composedestinations.generated.scratchcard.destinations.ValidateCardScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.snowcat.assignmentapp.R
import com.snowcat.assignmentapp.navigation.HomeGraph
import com.snowcat.designsystem.Spacing
import com.snowcat.designsystem.components.BasicButton
import com.snowcat.designsystem.components.DEFAULT_CARD_HEIGHT_PERCENTAGE
import com.snowcat.designsystem.components.ScratchCard

/**
 * Home Screen Composable.
 */
@Destination<HomeGraph>(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Spacing.grid6)
    ) {
        Spacer(Modifier.weight(1f))
        ScratchCard(
            modifier = Modifier.fillMaxHeight(DEFAULT_CARD_HEIGHT_PERCENTAGE),
            code = state.code,
            scratched = state.isScratched
        )
        Spacer(Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth()) {
            BasicButton(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.button_scratch),
                state = state.scratchButtonState
            ) {
                navigator.navigate(ScratchCardScreenDestination)
            }

            Spacer(Modifier.width(Spacing.grid4))

            BasicButton(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.button_validate),
                state = state.validateButtonState
            ) {
                navigator.navigate(ValidateCardScreenDestination)
            }
        }
    }
}
