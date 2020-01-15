package com.ups.data.model

data class CarListResponse(
    val `data`: List<Car>,
    val message: String,
    val status: String
)