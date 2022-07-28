package com.gscapin.blogappcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gscapin.blogappcompose.screens.Auth.CreateAccountScreen
import com.gscapin.blogappcompose.screens.Auth.LoginScreen
import com.gscapin.blogappcompose.screens.home.HomeScreen
import com.gscapin.blogappcompose.screens.welcome.WelcomeScreen

@Composable
fun BlogNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BlogScreens.WelcomeScreen.name
    ) {
        composable(BlogScreens.WelcomeScreen.name) {
            WelcomeScreen(navController)
        }
        composable(BlogScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable((BlogScreens.CreateAccountScreen.name)) {
            CreateAccountScreen(navController)
        }
        composable(BlogScreens.HomeScreen.name) {
            HomeScreen()
        }
    }
}