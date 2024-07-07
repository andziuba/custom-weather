package com.example.customweather.api.data

interface WeatherData {
    val currentTemperature: Double
    val feelsLikeCurrentTemperature: Double
    val uvIndex: Double?  // Not all APIs provide UV index
}
