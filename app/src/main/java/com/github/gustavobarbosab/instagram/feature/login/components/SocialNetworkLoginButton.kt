package com.github.gustavobarbosab.instagram.feature.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.composition.customColors
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.sizing

@Composable
fun SocialNetworkLoginButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.sizing.size48),
        border = ButtonDefaults.outlinedButtonBorder().copy(
            brush = MaterialTheme.customColors.gradient
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(
                brush = MaterialTheme.customColors.gradient
            ),
        )
    }
}

@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        SocialNetworkLoginButton(
            text = "Facebook",
            onClick = {},
        )
    }
}