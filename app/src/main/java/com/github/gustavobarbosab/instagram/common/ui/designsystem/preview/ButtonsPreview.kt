package com.github.gustavobarbosab.instagram.common.ui.designsystem.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.button.PrimaryButton
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.button.SecondaryButton
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.button.TertiaryButton
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme

@Composable
internal fun ButtonsPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        Text(
            text = "Buttons",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = "Primary Button",
                onClick = {}
            )
            SecondaryButton(
                modifier = Modifier.weight(1f),
                text = "Secondary Button",
                onClick = {}
            )
        }

        TertiaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Tertiary Button",
            onClick = {}
        )
    }
}

@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        ButtonsPreview()
    }
}