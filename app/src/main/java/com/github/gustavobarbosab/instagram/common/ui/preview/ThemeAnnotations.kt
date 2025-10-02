package com.github.gustavobarbosab.instagram.common.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showBackground = true,
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Preview(
    showBackground = true,
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true,
    backgroundColor = 0xFF111111
)
annotation class ThemePreview
