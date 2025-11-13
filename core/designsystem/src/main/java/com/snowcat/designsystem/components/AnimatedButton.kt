package com.snowcat.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snowcat.designsystem.AssignmentAppTheme
import com.snowcat.designsystem.R
import com.snowcat.designsystem.Spacing
import timber.log.Timber

/**
 * Animated Button Composable.
 */
@Composable
fun AnimatedButton(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.ENABLED,
    onClick: () -> Unit
) {
    Timber.e("XXX -> $state")
    val color by animateColorAsState(
        when (state) {
            ButtonState.DISABLED -> MaterialTheme.colorScheme.surfaceContainer
            else -> MaterialTheme.colorScheme.primary
        }
    )
    Box(
        modifier = Modifier
            .height(48.dp)
            .widthIn(min = 48.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(color = color)
            .clickable(
                enabled = state == ButtonState.ENABLED || state == ButtonState.ERROR,
                onClick = onClick
            )
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = state == ButtonState.ENABLED || state == ButtonState.DISABLED,
            enter = expandHorizontally(expandFrom = Alignment.CenterHorizontally, clip = true),
            exit = shrinkHorizontally(shrinkTowards = Alignment.CenterHorizontally, clip = true)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        AnimatedVisibility(
            visible = state == ButtonState.LOADING,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 3.dp
            )
        }
        AnimatedVisibility(
            visible = state == ButtonState.SUCCESS ||
                    state == ButtonState.FAILURE ||
                    state == ButtonState.ERROR,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Timber.e("XXX -> state in status: $state")
            Icon(
                imageVector = getIcon(state),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null
            )
        }
    }
}

/**
 * Get icon for given [ButtonState].
 */
@Composable
fun getIcon(state: ButtonState): ImageVector {
    return when (state) {
        ButtonState.SUCCESS -> ImageVector.vectorResource(R.drawable.ic_check_24)
        ButtonState.FAILURE -> ImageVector.vectorResource(R.drawable.ic_fail_24)
        ButtonState.ERROR -> ImageVector.vectorResource(R.drawable.ic_refresh_24)
        else -> ImageVector.vectorResource(R.drawable.ic_fail_24)
    }
}

@Preview
@Composable
private fun AnimatedButtonPreview() {
    AssignmentAppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.grid4),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedButton(
                text = "Submit",
                state = ButtonState.ENABLED,
                onClick = {}
            )
            AnimatedButton(
                text = "Submit",
                state = ButtonState.DISABLED,
                onClick = {}
            )
            AnimatedButton(
                text = "Submit",
                state = ButtonState.LOADING,
                onClick = {}
            )
            AnimatedButton(
                text = "Submit",
                state = ButtonState.SUCCESS,
                onClick = {}
            )
            AnimatedButton(
                text = "Submit",
                state = ButtonState.ERROR,
                onClick = {}
            )
        }
    }
}

/**
 * Button states for [AnimatedButton] and [BasicButton].
 */
enum class ButtonState {
    ENABLED,
    DISABLED,
    LOADING,
    SUCCESS,
    FAILURE,
    ERROR
}
