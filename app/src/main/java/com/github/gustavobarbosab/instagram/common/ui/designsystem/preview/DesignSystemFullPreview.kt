package com.github.gustavobarbosab.instagram.common.ui.designsystem.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme

@ThemePreview
@Composable
private fun FullPreview() {
    InstagramTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Color Palette
            ColorPalettePreview()
            SectionDivider()

            // Typography
            TypographySection()
            SectionDivider()

            // Components
            ComponentsPreview()
            SectionDivider()

            // Buttons
            ButtonsPreview()
            SectionDivider()

            // Cards
            CardsPreview()
            SectionDivider()
        }
    }
}

@Composable
private fun SectionDivider() {
    HorizontalDivider(
        Modifier
            .background(color = MaterialTheme.colorScheme.onBackground)
            .height(1.dp)
    )
}
