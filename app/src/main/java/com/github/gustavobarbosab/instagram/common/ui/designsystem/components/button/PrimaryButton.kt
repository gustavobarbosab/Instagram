package com.github.gustavobarbosab.instagram.common.ui.designsystem.components.button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
) {
    BaseButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        isLoading = isLoading,
        colors = ButtonDefaults.buttonColors(),
        shapes = MaterialTheme.shapes.small,
        border = null,
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
        PrimaryButton(
            text = "Testing",
            onClick = {},
            isLoading = buttonPreview.loading,
            enabled = buttonPreview.enabled
        )
    }
}
