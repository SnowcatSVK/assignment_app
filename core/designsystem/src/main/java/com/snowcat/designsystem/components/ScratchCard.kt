package com.snowcat.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snowcat.designsystem.AssignmentAppTheme
import com.snowcat.designsystem.R
import com.snowcat.designsystem.Spacing

/**
 * Scratch Card Composable.
 */
@Composable
fun ScratchCard(
    modifier: Modifier = Modifier,
    code: String,
    scratched: Boolean = false
) {
    val fieldColor by animateColorAsState(
        if (scratched) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.surface
        }
    )
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primary)
            .padding(Spacing.grid6),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text = stringResource(R.string.scratch_field_label),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(MaterialTheme.shapes.small)
                .background(fieldColor)
                .padding(Spacing.grid4),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = code,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun ScratchCardPreview() {
    AssignmentAppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.grid6)
        ) {
            ScratchCard(
                modifier = Modifier.height(250.dp),
                code = "ABC123DEF456",
                scratched = false
            )
            ScratchCard(
                modifier = Modifier.height(250.dp),
                code = "ABC123DEF456",
                scratched = true
            )
        }
    }
}

const val DEFAULT_CARD_HEIGHT_PERCENTAGE = 0.25f
