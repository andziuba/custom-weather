package com.example.customweather.repository

import com.example.customweather.api.data.WeatherData

class CurrentWeatherRepository(private val currentWeatherDataFetcher: CurrentWeatherDataFetcher) {

    suspend fun fetchWeatherData(location: String): List<WeatherData> {
        return currentWeatherDataFetcher.fetchCurrentWeatherData(location)
    }
}
