package com.github.gustavobarbosab.instagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.feature.chat.ChatInboxRoute
import com.github.gustavobarbosab.instagram.feature.chat.ChatInboxScreen
import com.github.gustavobarbosab.instagram.feature.home.HomeRoute
import com.github.gustavobarbosab.instagram.feature.home.HomeScreen
import com.github.gustavobarbosab.instagram.feature.login.LoginRoute
import com.github.gustavobarbosab.instagram.feature.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()

            InstagramTheme {
                val navController = rememberNavController()
                val navGraph: NavGraph = remember(navController) {
                    navController.createGraph(startDestination = LoginRoute::class) {
                        composable(route = LoginRoute::class) { LoginScreen(navController) }
                        composable(route = HomeRoute::class) { HomeScreen(navController) }
                        composable(route = ChatInboxRoute::class) { ChatInboxScreen(navController) }
                    }
                }
                NavHost(navController = navController, graph = navGraph)
            }
        }
    }
}