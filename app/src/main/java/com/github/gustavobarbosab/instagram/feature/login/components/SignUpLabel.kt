package com.github.gustavobarbosab.instagram.feature.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme

@Composable
fun SignUpPrompt(
    questionLabel: String,
    signUpLabel: String,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = questionLabel,
            style = MaterialTheme.typography.bodyMedium
        )
        TextButton(onClick = onSignUpClick) {
            Text(
                text = signUpLabel,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        SignUpPrompt(
            questionLabel = "Don't have an account?",
            signUpLabel = "Sign up",
            onSignUpClick = {}
        )
    }
}