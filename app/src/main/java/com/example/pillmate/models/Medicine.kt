package com.example.pillmate.models

data class Medicine(
    val id: Int,
    val name: String,
    val dosageForm: String, // Ensure this property exists
    val quantity: Int,
    val frequency: String,
    val time: String
)