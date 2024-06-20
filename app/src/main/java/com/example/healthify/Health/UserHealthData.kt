package com.example.healthify.Health

import android.location.Address

data class UserHealthData(
    var medicalID: String="",
    var name: String="",
    var bloodGroup: String="",
    var gender: String="",
    var healthCondition: String="",
    var emergencyPhoneNumber: Int=0,
    var address: String="",
    var allergies: String="",
    var medications: String=""
)
