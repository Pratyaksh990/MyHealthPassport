package com.example.healthify.Health

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.TopAppBar
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HealthViewModel: ViewModel(){
    fun saveHealthData(
        userHealthData: UserHealthData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore
            .collection("health")
            .document(userHealthData.medicalID)

        try {
            firestoreRef.set(userHealthData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully Saved your Data", Toast.LENGTH_SHORT)
                        .show()
                }
        } catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveHealthData(
        medicalID: String,
        context: Context,
        data: (UserHealthData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
         val firestoreRef = Firebase.firestore
             .collection("health")
             .document(medicalID)

        try {
            firestoreRef.get()
                .addOnSuccessListener {
                    if(it.exists()){
                        val userHealthData = it.toObject<UserHealthData>()!!
                        data(userHealthData)
                    } else{
                        Toast.makeText(context, "No Health Data found in Database", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun delete(
        medicalID: String,
        context: Context,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore
            .collection("health")
            .document(medicalID)

        try {
            firestoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully Deleted Data", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}