package com.github.gustavobarbosab.instagram.common.ui.designsystem.button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    BaseButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        isLoading = isLoading,
        colors = ButtonDefaults.outlinedButtonColors(),
        border = ButtonDefaults.outlinedButtonBorder(enabled),
        shapes = MaterialTheme.shapes.small,
        text = text
    )
}

@ThemePreview
@Composable
private fun Preview(
    @PreviewParameter(BaseButtonPreviewParameterProvider::class)
    buttonPreview: BaseButtonComponentPreview,
) {
    InstagramTheme {
        SecondaryButton(
            text = "Testing",
            onClick = {},
            isLoading = buttonPreview.loading,
            enabled = buttonPreview.enabled
        )
    }
}