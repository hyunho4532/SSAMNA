package com.asetec.presentation.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asetec.presentation.ui.login.LoginScreen
import com.asetec.presentation.ui.splash.OnBoardingScreen
import com.asetec.presentation.ui.splash.SplashScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("home") {
            OnBoardingScreen(navController = navController)
        }
        composable("login") {
            LoginScreen()
        }
    }
}