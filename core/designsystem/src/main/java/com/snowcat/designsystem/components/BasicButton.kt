package com.snowcat.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Basic not animated button.
 */
@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.ENABLED,
    onClick: () -> Unit
) {
    val backgroundColor = when (state) {
        ButtonState.DISABLED -> MaterialTheme.colorScheme.surfaceContainer
        else -> MaterialTheme.colorScheme.primary
    }
    Box(
        modifier = Modifier
            .height(48.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(backgroundColor)
            .clickable(enabled = state == ButtonState.ENABLED, onClick = onClick)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
