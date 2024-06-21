package com.example.healthify.Health

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthify.Navigation.Screen

@Composable
fun MainHealthActivity(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()) {
            Button(onClick = { navController.navigate(Screen.HealthInfo.route) }, modifier = Modifier.padding(20.dp)) {
                Text(text = "Save Data")
            }
        Button(onClick = { navController.navigate(Screen.GetHealthInfo.route) }, modifier = Modifier.padding(20.dp)) {
                Text(text = "Retrieve Data")
            }
    }
}

@Preview(showBackground = true)
@Composable
fun MainHealthActivityPreview(){
    MainHealthActivity(navController = rememberNavController())
}