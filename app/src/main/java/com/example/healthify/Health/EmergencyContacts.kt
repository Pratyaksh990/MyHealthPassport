package com.example.healthify.Health

import android.content.Intent
import android.graphics.Paint.Align
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun EmergencyContactsList(contacts: List<EmergencyContact>) {
    val context = LocalContext.current

    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF44A6FC), Color(0xFF75F8F2))
    )

    Column(Modifier.background(color = Color.White).padding(0.dp,25.dp)) {
        Text(text = "Emergency Contact List",
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(8.dp,8.dp),
            textDecoration = TextDecoration.Underline)

        LazyColumn(modifier = Modifier.padding(16.dp)) {

            items(contacts) { contact ->
                Column(modifier = Modifier.padding(vertical = 8.dp).clip(RoundedCornerShape(10.dp,10.dp,20.dp,20.dp))) {
                    Text(
                        text = contact.name,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .background(color = getRandomColor())
                            .fillMaxWidth()
                            .padding(10.dp, 10.dp),
                        textAlign = TextAlign.Center
                    )
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${contact.phoneNumber}")
                            }
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp, 10.dp)
                    ) {
                        Text(text = "Call ${contact.name}",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontFamily = FontFamily.SansSerif)
                    }
                }
            }
        }
    }
}

@Composable
fun EmergencyContactsListPreview(navController: NavController) {
    val contacts = listOf(
        EmergencyContact("Police", "100"),
        EmergencyContact("Ambulance", "102"),
        EmergencyContact("Fire Department", "101"),
        EmergencyContact("Poison Control", "1066"),
        EmergencyContact("Electricity Emergency", "115"),
        EmergencyContact("Women Helpline", "1091"),
        EmergencyContact("Disaster Management ( N.D.M.A )", "1078"),
        EmergencyContact("Senior Citizen Helpline", "14567"),
        EmergencyContact("Railway Accident Emergency Service", "1072"),
        EmergencyContact("Road Accident Emergency Service", "1912"),
    )

    MaterialTheme {
        EmergencyContactsList(contacts = contacts)
    }
}

@Composable
fun getRandomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1.0f
    )
}

