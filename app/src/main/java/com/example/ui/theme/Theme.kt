package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

fun createCyberColorScheme(accent: Color = NeonRed) = darkColorScheme(
    primary = accent,
    onPrimary = Color.Black,
    primaryContainer = accent.copy(alpha = 0.2f),
    onPrimaryContainer = Color.White,
    secondary = NeonCyan,
    onSecondary = Color.Black,
    secondaryContainer = NeonCyan.copy(alpha = 0.15f),
    onSecondaryContainer = Color.White,
    tertiary = NeonPurple,
    onTertiary = Color.White,
    background = CyberBlack,
    onBackground = TextPrimary,
    surface = CyberSurface,
    onSurface = TextPrimary,
    surfaceVariant = CyberSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = CyberBorder,
    outlineVariant = CyberBorderGlow
)

@Composable
fun XERTheme(
    accentColor: Color = NeonRed,
    content: @Composable () -> Unit
) {
    val colorScheme = createCyberColorScheme(accentColor)
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
