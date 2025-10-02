package com.github.gustavobarbosab.instagram.common.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Instagram Light Theme
private val InstagramLightColorScheme = lightColorScheme(
    primary = InstagramBlue,
    onPrimary = White,
    primaryContainer = LightBlue,
    onPrimaryContainer = DarkBlue,
    
    secondary = InstagramGradientStart,
    onSecondary = White,
    secondaryContainer = LightPurple,
    onSecondaryContainer = DarkPurple,
    
    tertiary = InstagramGradientMiddle,
    onTertiary = White,
    tertiaryContainer = LightPink,
    onTertiaryContainer = DarkPink,
    
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = LightGray,
    onSurfaceVariant = DarkGray,
    
    outline = LightGray,
    outlineVariant = VeryLightGray,
    
    error = ErrorRed,
    onError = White,
    errorContainer = LightRed,
    onErrorContainer = DarkRed,
    
    inverseSurface = Black,
    inverseOnSurface = White,
    inversePrimary = LightBlue,
    
    surfaceTint = InstagramBlue,
    scrim = Black
)

// Instagram Dark Theme
private val InstagramDarkColorScheme = darkColorScheme(
    primary = LightBlue,
    onPrimary = DarkBlue,
    primaryContainer = DarkBlue,
    onPrimaryContainer = LightBlue,
    
    secondary = LightPurple,
    onSecondary = DarkPurple,
    secondaryContainer = DarkPurple,
    onSecondaryContainer = LightPurple,
    
    tertiary = LightPink,
    onTertiary = DarkPink,
    tertiaryContainer = DarkPink,
    onTertiaryContainer = LightPink,
    
    background = DarkBackground,
    onBackground = White,
    surface = DarkSurface,
    onSurface = White,
    surfaceVariant = DarkGray,
    onSurfaceVariant = LightGray,
    
    outline = DarkGray,
    outlineVariant = VeryDarkGray,
    
    error = LightRed,
    onError = DarkRed,
    errorContainer = DarkRed,
    onErrorContainer = LightRed,
    
    inverseSurface = White,
    inverseOnSurface = DarkBackground,
    inversePrimary = InstagramBlue,
    
    surfaceTint = LightBlue,
    scrim = Black
)

@Composable
fun InstagramTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disabled to use Instagram branding
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> InstagramDarkColorScheme
        else -> InstagramLightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = InstagramTypography,
        shapes = InstagramShapes,
        content = content
    )
}