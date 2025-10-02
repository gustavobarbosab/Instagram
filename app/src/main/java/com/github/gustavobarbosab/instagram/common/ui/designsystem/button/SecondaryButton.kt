package com.github.gustavobarbosab.instagram.common.ui.designsystem.button

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Text(text)
    }
}