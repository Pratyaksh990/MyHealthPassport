package com.example.healthify.Health

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthify.R


//data class PatientData(
//    val medicalID: String,
//    val name: String,
//    val bloodGroup: String,
//    val age: Int,
//    val gender: String,
//    val healthCondition: String,
//    val emergencyPhoneNumber: Int,
//    val address: String,
//    val allergies: String,
//    val medications: String
//)

@Composable
fun PatientDetails(navController: NavController, patientData: String, healthViewModel: HealthViewModel) {

    var medicalID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var bloodGroup: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableIntStateOf(0) }
    var gender: String by remember { mutableStateOf("") }
    var healthCondition: String by remember { mutableStateOf("") }
    var emergencyPhoneNumber: String by remember { mutableStateOf("") }
    var emergencyPhoneNumberInt: Int by remember { mutableIntStateOf(0) }
    var address: String by remember { mutableStateOf("") }
    var allergies: String by remember { mutableStateOf("") }
    var medications: String by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scrollView = rememberScrollState()

    healthViewModel.retrieveHealthData(
        medicalID = patientData,
        context = context
    ) { data ->
        name = data.name
        bloodGroup = data.bloodGroup
        age = data.age.toString()
        ageInt = data.age.toInt()
        gender = data.gender
        healthCondition = data.healthCondition
        emergencyPhoneNumber = data.emergencyPhoneNumber.toString()
        emergencyPhoneNumberInt = data.emergencyPhoneNumber.toInt()
        address = data.address
        allergies = data.allergies
        medications = data.medications
    }

    val medicaldetails = listOf(
        name,
        bloodGroup,
        age,
        gender,
        healthCondition,
        emergencyPhoneNumber,
        address,
        allergies,
        medications
    )

    val backgroundPainter: Painter = painterResource(id = R.drawable.healthcare)

    Box {
            Image(
                painter = backgroundPainter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollView)
            ) {
                Text(
                    text = "Patient Details", fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(color = Color.Transparent)
                    //     .border(2.dp, color = Color.Black, shape = RectangleShape)
                ) {
                    items(medicaldetails) { data ->
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color.Cyan, shape = RectangleShape)
                                .border(1.dp, color = Color.Blue, shape = RectangleShape)
                                .fillMaxWidth(),
                            colors = CardColors(Color.White, Color.Black, Color.Blue, Color.Magenta)
                        ) {
                            Text(
                                text = "Medical ID: ${medicalID}",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(text = "Name: ${name}", modifier = Modifier.padding(5.dp))
                            Text(
                                text = "Blood Group: ${bloodGroup}",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(text = "Age: ${age}", modifier = Modifier.padding(5.dp))
                            Text(text = "Gender: ${gender}", modifier = Modifier.padding(5.dp))
                            Text(
                                text = "Health Condition: ${healthCondition}",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Emergency Phone Number: ${emergencyPhoneNumber}",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Address: ${address}",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Allergies: ${allergies}",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Medications: ${medications}",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PatientPreview(){
    PatientDetails(navController = rememberNavController(), patientData = "ak123", healthViewModel = HealthViewModel())
}


//@Preview(showBackground = true)
//@Composable
//fun PatientDetailsPreview()
//{
//    val sampleData = listOf(
//        PatientData(
//            medicalID = "123455",
//            name = "John Doe",
//            bloodGroup = "A+",
//            age = 30,
//            gender = "Male",
//            healthCondition = "Healthy",
//            emergencyPhoneNumber = 1234557590,
//            address = "123 Main St",
//            allergies = "None",
//            medications = "None"
//        ),
//        PatientData(
//            medicalID = "554321",
//            name = "Jane Smith",
//            bloodGroup = "O-",
//            age = 25,
//            gender = "Female",
//            healthCondition = "Asthma",
//            emergencyPhoneNumber = 957554720,
//            address = "455 Elm St",
//            allergies = "Peanuts",
//            medications = "Inhaler"
//        ),
//        PatientData(
//            medicalID = "554321",
//            name = "Jane Smith",
//            bloodGroup = "O-",
//            age = 25,
//            gender = "Female",
//            healthCondition = "Asthma",
//            emergencyPhoneNumber = 957554720,
//            address = "455 Elm St",
//            allergies = "Peanuts",
//            medications = "Inhaler"
//        ),
//        PatientData(
//            medicalID = "554321",
//            name = "Jane Smith",
//            bloodGroup = "O-",
//            age = 25,
//            gender = "Female",
//            healthCondition = "Asthma",
//            emergencyPhoneNumber = 957554720,
//            address = "455 Elm St",
//            allergies = "Peanuts",
//            medications = "Inhaler"
//        )
//    )
//    PatientDetails(navController = rememberNavController(), patientData = sampleData)
//}