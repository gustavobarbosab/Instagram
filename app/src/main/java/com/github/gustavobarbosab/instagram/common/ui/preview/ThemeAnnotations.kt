package com.github.gustavobarbosab.instagram.common.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import com.github.gustavobarbosab.instagram.common.ui.theme.DarkGray


@Preview(
    showBackground = true,
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    showBackground = true,
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    backgroundColor = 0xFF000000
)
annotation class ThemePreview
