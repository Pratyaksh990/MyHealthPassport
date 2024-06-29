package com.example.healthify.Navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthify.DoctorLogin.DoctorLogin
import com.example.healthify.Health.EmergencyContact
import com.example.healthify.Health.EmergencyContactsList
import com.example.healthify.Health.EmergencyContactsListPreview
import com.example.healthify.Health.GetHealthInfo
import com.example.healthify.Health.HealthInfo
import com.example.healthify.Health.HealthViewModel
import com.example.healthify.Health.MainHealthActivity
import com.example.healthify.Health.PatientDetails
import com.example.healthify.Health.SplashScreen
import com.example.healthify.SignInSignUp.SignInScreen
import com.example.healthify.SignInSignUp.SignUpScreen
import com.google.api.Authentication
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

@Composable
fun NavGraph(navController: NavController, healthViewModel: HealthViewModel) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    NavHost(navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SignUp.route) {
            SignUpScreen(navController, auth)
        }
        composable(Screen.Login.route) {
            SignInScreen(navController, auth)
        }
        composable(Screen.DoctorLogin.route){
            DoctorLogin(navController, auth)
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
        composable(Screen.GetHealthInfo.route){
            GetHealthInfo(navController = navController, healthViewModel = healthViewModel)
        }

        composable(Screen.PatientDetails.route)
        {
            backStackEntry ->
            backStackEntry.arguments?.getString("patientData")
                ?.let { PatientDetails(navController = navController, patientData = it, healthViewModel = HealthViewModel()) }
        }

        composable(Screen.EmergencyContacts.route){
            EmergencyContactsListPreview(navController = navController)
        }
        composable(Screen.SplashScreen.route)
        {
            SplashScreen(navController = navController)
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
