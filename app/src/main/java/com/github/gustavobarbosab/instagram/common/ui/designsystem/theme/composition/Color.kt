package com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.composition

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Brush
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramGradientEnd
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramGradientStart


data class CustomColors(
    val gradient: Brush = Brush.horizontalGradient(
        colors = listOf(InstagramGradientStart, InstagramGradientEnd)
    ),
)

val LocalCustomColors = compositionLocalOf { CustomColors() }

val MaterialTheme.customColors: CustomColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColors.current