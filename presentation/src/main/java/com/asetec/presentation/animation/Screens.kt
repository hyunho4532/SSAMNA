package com.asetec.presentation.animation

sealed class Screens(
    val route: String
) {
    data object HomeScreen: Screens("homeScreen")
    data object AnalyzeScreen: Screens("analyzeScreen")
    data object ProfileScreen: Screens("profileScreen")
}