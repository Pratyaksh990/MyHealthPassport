package com.example.healthify.Health

import android.widget.ScrollView
import android.widget.Scroller
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthify.Navigation.Screen
import com.example.healthify.R

@Composable
fun GetHealthInfo(
    navController: NavController,
    healthViewModel: HealthViewModel
){
    var medicalID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var bloodGroup: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableStateOf(0) }
    var gender: String by remember { mutableStateOf("") }
    var healthCondition: String by remember { mutableStateOf("") }
    var emergencyPhoneNumber: String by remember { mutableStateOf("") }
    var emergencyPhoneNumberInt: Int by remember { mutableStateOf(0) }
    var address: String by remember { mutableStateOf("") }
    var allergies: String by remember { mutableStateOf("") }
    var medications: String by remember { mutableStateOf("") }

    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF00BCD4), Color(0xFF1E88E5))
    )
    val backgroundPainter: Painter = painterResource(id = R.drawable.healthcare)

    val context = LocalContext.current
    val scrollView = rememberScrollState()

    Box {
        Image(
            painter = backgroundPainter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.matchParentSize(),
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollView),
                horizontalArrangement = Arrangement.Start,
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 60.dp, end = 60.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollView),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.6f).background(Color.White),
                        value = medicalID,
                        onValueChange = { medicalID = it },
                        label = {
                            Text(text = "MedicalID")
                        }
                    )
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .background(gradient, shape = RoundedCornerShape(8.dp))
                            .width(100.dp),
                        onClick = {
                            healthViewModel.retrieveHealthData(
                                medicalID = medicalID,
                                context = context
                            ) { data ->
                                name = data.name
                                bloodGroup = data.bloodGroup
                                age = data.age.toString()
                                ageInt = ageInt.toInt()
                                gender = data.gender
                                healthCondition = data.healthCondition
                                emergencyPhoneNumber = data.emergencyPhoneNumber.toString()
                                emergencyPhoneNumberInt = data.emergencyPhoneNumber.toInt()
                                address = data.address
                                allergies = data.allergies
                                medications = data.medications
                            }
                        }
                    ) {
                        Text(text = "Get Data", textAlign = TextAlign.Center)
                    }
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = medicalID,
                    onValueChange = { medicalID = it },
                    label = {
                        Text(text = "MedicalID")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(text = "Name")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = bloodGroup,
                    onValueChange = { bloodGroup = it },
                    label = {
                        Text(text = "Blood Group")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = age,
                    onValueChange = {
                        age = it
                        if (age.isNotEmpty()) {
                            ageInt = age.toInt()
                        }
                    },
                    label = {
                        Text(text = "Age")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = gender,
                    onValueChange = { gender = it },
                    label = {
                        Text(text = "Gender")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = healthCondition,
                    onValueChange = { healthCondition = it },
                    label = {
                        Text(text = "Health Condition")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = emergencyPhoneNumber,
                    onValueChange = {
                        emergencyPhoneNumber = it
                        if (emergencyPhoneNumber.isNotEmpty()) {
                            emergencyPhoneNumberInt = emergencyPhoneNumber.toInt()
                        }
                    },
                    label = {
                        Text(text = "Emergency Phone Number")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = address,
                    onValueChange = { address = it },
                    label = {
                        Text(text = "Address")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = allergies,
                    onValueChange = { allergies = it },
                    label = {
                        Text(text = "Allergies")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    value = medications,
                    onValueChange = { medications = it },
                    label = {
                        Text(text = "Medications")
                    }
                )
                ExtendedFloatingActionButton(modifier = Modifier
                    .padding(10.dp)
                    .width(100.dp)
                    .background(gradient, shape = RoundedCornerShape(8.dp))
                    .size(50.dp),
                    onClick = {
                        healthViewModel.delete(
                            medicalID = medicalID,
                            context = context,
                            navController = navController
                        )
                    }) {
                    Text(text = "Delete")
                }
                ExtendedFloatingActionButton(modifier = Modifier
                    .padding(bottom = 10.dp)
                    .background(gradient, shape = RoundedCornerShape(8.dp))
                    .fillMaxSize(),
                    onClick = {
                        val patientData = medicalID
                        navController.navigate("patient_details/$patientData")
                    }) {
                    Text(text = "Open Data in new Screen")
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GetHealthInfoPreview()
{
    GetHealthInfo(navController = rememberNavController(), healthViewModel = HealthViewModel())
}