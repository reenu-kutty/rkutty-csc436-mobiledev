package com.finalproject.pebble.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

val whiteTextTypography = Typography(
    displayLarge = TextStyle(color = Color.White),
    displayMedium = TextStyle(color = Color.White),
    displaySmall = TextStyle(color = Color.White),
    headlineLarge = TextStyle(color = Color.White),
    headlineMedium = TextStyle(color = Color.White),
    headlineSmall = TextStyle(color = Color.White),
    titleLarge = TextStyle(color = Color.White),
    titleMedium = TextStyle(color = Color.White),
    titleSmall = TextStyle(color = Color.White),
    bodyLarge = TextStyle(color = Color.White),
    bodyMedium = TextStyle(color = Color.White),
    bodySmall = TextStyle(color = Color.White),
    labelLarge = TextStyle(color = Color.White),
    labelMedium = TextStyle(color = Color.White),
    labelSmall = TextStyle(color = Color.White)
)

@Composable
fun PebbleTheme(
    content: @Composable() () -> Unit
) {
    val colors = DarkColors

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = whiteTextTypography,
    )
}