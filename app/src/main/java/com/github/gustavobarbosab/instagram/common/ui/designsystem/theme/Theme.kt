package com.github.gustavobarbosab.instagram.common.ui.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.composition.CustomColors
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.composition.LocalCustomColors
import com.github.gustavobarbosab.instagram.common.ui.theme.Black
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkBackground
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkBlue
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkGray
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkPink
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkPurple
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkRed
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkSurface
import com.github.gustavobarbosab.instagram.common.ui.theme.ErrorRed
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramBlue
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramGradientMiddle
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramGradientStart
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramShapes
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTypography
import com.github.gustavobarbosab.instagram.common.ui.theme.LightBlue
import com.github.gustavobarbosab.instagram.common.ui.theme.LightGray
import com.github.gustavobarbosab.instagram.common.ui.theme.LightPink
import com.github.gustavobarbosab.instagram.common.ui.theme.LightPurple
import com.github.gustavobarbosab.instagram.common.ui.theme.LightRed
import com.github.gustavobarbosab.instagram.common.ui.theme.VeryDarkGray
import com.github.gustavobarbosab.instagram.common.ui.theme.VeryLightGray
import com.github.gustavobarbosab.instagram.common.ui.theme.White
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.LocalSpacing
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.Spacing

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

    background = VeryLightGray,
    onBackground = DarkGray,
    surface = White,
    onSurface = Black,
    surfaceVariant = LightGray,
    onSurfaceVariant = DarkGray,
    surfaceContainerHighest = LightGray,

    outline = InstagramBlue,
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
    surfaceContainerHighest = VeryDarkGray,

    outline = LightBlue,
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
    spacing: Spacing = Spacing(),
    customColors: CustomColors = CustomColors(),
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

    CompositionLocalProvider(
        LocalSpacing provides spacing,
        LocalCustomColors provides customColors,
        LocalContentColor provides colorScheme.onBackground
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = InstagramTypography,
            shapes = InstagramShapes,
            content = content
        )
    }
}
