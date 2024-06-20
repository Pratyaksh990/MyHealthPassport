package com.example.healthify.SignInSignUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.navigation.NavController
import com.example.healthify.Navigation.Screen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpScreen(navController: NavController, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") }
        )
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") }
        )
        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }
        Button(onClick = {
            if (password == confirmPassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate(Screen.Home.route)
                        } else {
                            errorMessage = task.exception?.message
                        }
                    }
            } else {
                errorMessage = "Passwords do not match"
            }
        }) {
            Text(text = "Sign Up")
        }
        TextButton(onClick = { navController.navigate(Screen.Login.route) }) {
            Text(text = "Already have an account? Log In")
        }
    }
}
