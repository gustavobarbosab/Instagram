package com.github.gustavobarbosab.instagram.feature.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.toolbar.AppToolbar
import com.github.gustavobarbosab.instagram.common.ui.designsystem.toolbar.AppToolbarIcons
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object ChatInboxRoute : Route {
    override val name: String
        get() = "ChatInbox"
}

@Composable
fun ChatInboxScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            AppToolbar(
                text = "Inbox",
                startIcon = AppToolbarIcons.Back,
                startIconClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Hello, I'm the Inbox!")
        }
    }
}

@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        ChatInboxScreen(rememberNavController())
    }
}