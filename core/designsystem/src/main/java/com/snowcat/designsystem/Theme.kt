package com.snowcat.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = FunBlue,
    onPrimary = White,
    secondary = White,
    background = OffWhite,
    onBackground = Black,
    surface = Gray,
    onSurface = OffWhite,
    surfaceContainer = DarkGrey,
    primaryContainer = FunBlue,
    onPrimaryContainer = DarkGrey,
    secondaryContainer = Gray,
    onSecondaryContainer = OffWhite,
)

/**
 * Syngenta PPM DSM theme.
 *
 * @param content The content of the theme
 */
@Composable
fun AssignmentAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = AssignmentShapes,
        content = content
    )
}
