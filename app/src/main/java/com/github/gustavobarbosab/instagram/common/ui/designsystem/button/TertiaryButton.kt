package com.github.gustavobarbosab.instagram.common.ui.designsystem.button

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TertiaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Text(text)
    }
}