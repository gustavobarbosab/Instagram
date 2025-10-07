package com.github.gustavobarbosab.instagram.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.toolbar.AppToolbar
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.common.ui.designsystem.toolbar.AppToolbarIcons
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.sizing
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.spacing
import com.github.gustavobarbosab.instagram.core.navigation.Route
import com.github.gustavobarbosab.instagram.feature.chat.ChatInboxRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : Route {
    override val name: String
        get() = "Home"
}

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            AppToolbar(
                endIcon = AppToolbarIcons.Chat,
                endIconClick = {
                    navController.navigate(ChatInboxRoute)
                }
            ) {
                Row(
                    Modifier
                        .align(Alignment.CenterStart)
                        .padding(horizontal = MaterialTheme.spacing.space16),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space4)
                ) {
                    Text(
                        text = "Instagram",
                        fontFamily = FontFamily.Cursive,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Icon(
                        modifier = Modifier.size(MaterialTheme.sizing.size24),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Instagram",
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Hello, I'm the Login!")
        }
    }
}

@Preview(showSystemUi = true)
@ThemePreview
@Composable
private fun Preview() {
    InstagramTheme {
        HomeScreen(rememberNavController())
    }
}