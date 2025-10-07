package com.github.gustavobarbosab.instagram.feature.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.composition.sizing
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.composition.spacing
@Composable
fun DividerWithText(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onBackground,
            thickness = MaterialTheme.sizing.size1
        )

        Text(
            text = text,
            modifier = Modifier.padding(MaterialTheme.spacing.space16),
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onBackground,
            thickness = MaterialTheme.sizing.size1
        )
    }
}

@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        DividerWithText(
            text = "OR"
        )
    }
}