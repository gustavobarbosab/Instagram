package com.github.gustavobarbosab.instagram.common.ui.designsystem.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.sizing

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    isLoading: Boolean,
    colors: ButtonColors,
    border: BorderStroke?,
    shapes: Shape,
    text: String
) {
    Button(
        modifier = modifier.heightIn(min = MaterialTheme.sizing.size48),
        enabled = enabled,
        onClick = onClick,
        shape = shapes,
        colors = colors,
        border = border,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(MaterialTheme.sizing.size24),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = MaterialTheme.sizing.size2
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

data class BaseButtonComponentPreview(
    val enabled: Boolean,
    val loading: Boolean
)

class BaseButtonPreviewParameterProvider : PreviewParameterProvider<BaseButtonComponentPreview> {
    override val values = sequenceOf(
        BaseButtonComponentPreview(
            enabled = true,
            loading = true
        ),
        BaseButtonComponentPreview(
            enabled = true,
            loading = false
        ),
        BaseButtonComponentPreview(
            enabled = false,
            loading = true
        ),
        BaseButtonComponentPreview(
            enabled = false,
            loading = false
        )
    )
}