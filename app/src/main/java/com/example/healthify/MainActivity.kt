package com.example.healthify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthify.Health.HealthViewModel
import com.example.healthify.Navigation.NavGraph
import com.example.healthify.ui.theme.HealthifyTheme
import com.example.healthify.Navigation.NavGraph
import com.example.healthify.ui.theme.HealthifyTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private lateinit var navController : NavController
    private val healthViewModel: HealthViewModel by viewModels()

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            HealthifyTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController, healthViewModel = healthViewModel)
            }
        }
    }
}
