package com.example.customweather.statistics

import com.example.customweather.api.data.WeatherData

data class WeatherStatistics(
    val averageTemperature: Double,
    val averageFeelsLikeTemperature: Double,
    val averageUvIndex: Double
)

class CurrentWeatherStatistics {
    fun calculateStatistics(weatherDataList: List<WeatherData>): WeatherStatistics {
        val averageTemperature = weatherDataList.map { it.temperature }.average()
        val averageFeelsLikeTemperature = weatherDataList.map { it.feelsLikeCurrentTemperature }.average()
        val uvIndices = weatherDataList.mapNotNull { it.uvIndex }
        val averageUvIndex = if (uvIndices.isNotEmpty()) {
            uvIndices.average()
        } else {
            0.0
        }
        return WeatherStatistics(averageTemperature, averageFeelsLikeTemperature, averageUvIndex)
    }
}

