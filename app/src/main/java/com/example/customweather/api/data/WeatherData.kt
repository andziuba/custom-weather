package com.example.customweather.api.data

interface WeatherData {
    val temperature: Double
    val feelsLikeCurrentTemperature: Double
    val uvIndex: Double?  // Not all APIs provide UV index
}