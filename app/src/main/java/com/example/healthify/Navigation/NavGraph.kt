package com.example.healthify.Navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthify.Health.HealthInfo
import com.example.healthify.Health.HealthViewModel
import com.example.healthify.SignInSignUp.SignInScreen
import com.example.healthify.SignInSignUp.SignUpScreen
import com.example.healthify.Health.MainHealthActivity
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(navController: NavController, healthViewModel: HealthViewModel) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.SignUp.route) {
            SignUpScreen(navController, auth)
        }
        composable(Screen.Login.route) {
            SignInScreen(navController, auth)
        }
//        composable(Screen.Home.route) {
//            HomeScreen()
//        }
        composable(Screen.MainHealthActivity.route){
            MainHealthActivity(navController = navController)
        }
        composable(Screen.HealthInfo.route){
            HealthInfo(navController = navController, healthViewModel = healthViewModel)
        }
//        composable(Screen.AddDataScreen.route){
//            AddDataScreen(navController = navController, sharedViewModel = sharedViewModel)
//        }
    }
}

@Composable
fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "HELLO", color = Color.Black)
        }
    }
}
