package com.ups.data.model

data class CarDetailsResponse(
    val `data`: List<Data>,
    val message: String,
    val status: String
)