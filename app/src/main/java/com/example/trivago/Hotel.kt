package com.example.trivago

data class Hotel(
    val name: String,
    val location: String,
    val price: String,
    val pricePerNight: Int,
    val description: String,
    val rating: Float,
    val imageRes: Int,
    val amenities: List<String>
)
