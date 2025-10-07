package com.github.gustavobarbosab.instagram.common.ui.designsystem.components.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.spacing
@Composable
fun InstagramTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable ((Boolean) -> Unit)? = null,
    errorMessage: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onKeyboardAction: () -> Unit = {},
    defaultVisualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    enabled: Boolean = true
) {
    var toggleButton by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            textStyle = MaterialTheme.typography.labelLarge,
            leadingIcon = leadingIcon,
            trailingIcon = {
                Box(
                    Modifier.clickable { toggleButton = !toggleButton }
                ) {
                    trailingIcon?.invoke(toggleButton)
                }
            },
            visualTransformation = if (toggleButton) {
                VisualTransformation.None
            } else {
                defaultVisualTransformation
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardAction() }
            ),
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            isError = !errorMessage.isNullOrBlank(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
            )
        )

        if (!errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.space8,
                    top = MaterialTheme.spacing.space4
                )
            )
        }
    }
}

@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        InstagramTextField(
            value = "TestTestTestTestTestTestTestTestTestTestTestTest",
            onValueChange = { },
            placeholder = "Password",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password",
                )
            },
            trailingIcon = { isVisible ->
                Icon(
                    imageVector = if (isVisible) Icons.Default.CheckCircle else Icons.Default.AddCircle,
                    contentDescription = if (isVisible) "Hide password" else "Show password",
                )
            },
            defaultVisualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            errorMessage = ""
        )
    }
}

@ThemePreview
@Composable
private fun Preview2() {
    InstagramTheme {
        InstagramTextField(
            value = "",
            onValueChange = { },
            placeholder = "Password",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password",
                )
            },
            trailingIcon = { toggle ->
                Icon(
                    imageVector = if (toggle) Icons.Default.CheckCircle else Icons.Default.AddCircle,
                    contentDescription = if (toggle) "Hide password" else "Show password",
                )
            },
            defaultVisualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            errorMessage = "Teste"
        )
    }
}
