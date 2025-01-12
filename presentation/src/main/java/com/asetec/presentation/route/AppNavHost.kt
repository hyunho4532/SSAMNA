package com.asetec.presentation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asetec.domain.dto.user.AuthState
import com.asetec.presentation.ui.login.LoginScreen
import com.asetec.presentation.ui.login.ReportScreen
import com.asetec.presentation.ui.login.UserInfoScreen
import com.asetec.presentation.ui.splash.OnBoardingScreen
import com.asetec.presentation.ui.splash.SplashScreen
import kotlinx.serialization.json.Json

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
            LoginScreen(navController = navController)
        }

        composable(
            route = "userInfo?authState={authState}",
            arguments = listOf(navArgument("authState") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val authStateJson = backStackEntry.arguments?.getString("authState")
            val authState = Json.decodeFromString<AuthState>(authStateJson!!)

            UserInfoScreen(
                navController = navController,
                authState = authState
            )
        }

        composable(
            route = "report?userState={userState}",
            arguments = listOf(
                navArgument("userState") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val userStateJson = backStackEntry.arguments?.getString("userState")
            val userState = Json.decodeFromString<AuthState>(userStateJson!!)

            ReportScreen(userState)
        }
    }
}