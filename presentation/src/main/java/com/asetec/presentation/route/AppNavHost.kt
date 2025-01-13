package com.asetec.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asetec.domain.dto.user.User
import com.asetec.presentation.animation.Screens
import com.asetec.presentation.ui.login.LoginScreen
import com.asetec.presentation.ui.login.ReportScreen
import com.asetec.presentation.ui.login.UserInfoScreen
import com.asetec.presentation.ui.main.home.screen.HomeScreen
import com.asetec.presentation.ui.main.home.screen.ProfileScreen
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
            val user = Json.decodeFromString<User>(authStateJson!!)

            UserInfoScreen(
                navController = navController,
                user = user
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
            val userState = Json.decodeFromString<User>(userStateJson!!)

            ReportScreen(userState)
        }
    }
}

@Composable
fun ScreenNavigationConfiguration(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }

        composable(Screens.ProfileScreen.route) {
            ProfileScreen()
        }

    }

}