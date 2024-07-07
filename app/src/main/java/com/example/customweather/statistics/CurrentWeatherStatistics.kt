package com.example.customweather.statistics

import android.util.Log
import com.example.customweather.api.data.WeatherData

class CurrentWeatherStatistics {

    companion object {
        private const val TAG = "CurrentWeatherStatistics"
    }

    fun processCurrentWeatherData(weatherDataList: List<WeatherData>): CurrentWeatherProcessed {
        val currentTemperatures = weatherDataList.map { it.currentTemperature }
        Log.d(TAG, "Current Temperatures: $currentTemperatures")
        val averageCurrentTemperature = currentTemperatures.average()

        val feelsLikeTemperatures = weatherDataList.map { it.feelsLikeCurrentTemperature }
        Log.d(TAG, "Feels Like Temperatures: $feelsLikeTemperatures")
        val averageFeelsLikeTemperature = feelsLikeTemperatures.average()

        val uvIndices = weatherDataList.mapNotNull { it.uvIndex }
        Log.d(TAG, "UV Indices: $uvIndices")
        val averageUvIndex = if (uvIndices.isNotEmpty()) {
            uvIndices.average()
        } else {
            0.0
        }

        Log.d(TAG, "Average Current Temperature: $averageCurrentTemperature")
        Log.d(TAG, "Average Feels Like Temperature: $averageFeelsLikeTemperature")
        Log.d(TAG, "Average UV Index: $averageUvIndex")

        return CurrentWeatherProcessed(averageCurrentTemperature, averageFeelsLikeTemperature, averageUvIndex)
    }


}