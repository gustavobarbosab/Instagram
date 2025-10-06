package com.github.gustavobarbosab.instagram.common.ui.designsystem.button

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme

@Composable
fun TertiaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    BaseButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        isLoading = isLoading,
        colors = ButtonDefaults
            .textButtonColors()
            .copy(contentColor = MaterialTheme.colorScheme.onBackground),
        border = null,
        shapes = ButtonDefaults.textShape,
        text = text
    )
}


@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        TertiaryButton(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            text = "Testing",
            onClick = {},
            enabled = true,
        )
    }
}