package ru.aasmc.jetnotes.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

private val DarkColorPalette = darkColors(
    primary = rwGreen,
    primaryVariant = rwGreenDark,
    secondary = rwGreen
)

private val LightColorPalette = lightColors(
    primary = rwGreen,
    primaryVariant = rwGreenDark,
    secondary = rwGreen

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun JetNotesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}

/**
 * Allows changing between light and darth theme from the app's settings.
 */
object JetNotesThemeSettings {
    var isDarkThemeEnabled by mutableStateOf(false)
}




















