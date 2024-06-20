package com.example.healthify.Navigation

sealed class Screen(val route: String) {
    object SignUp : Screen("signup")
    object Login : Screen("login")
    object Home : Screen("home")
    object MainHealthActivity: Screen(route = "main_screen")
    object HealthInfo: Screen(route = "health_info")
}