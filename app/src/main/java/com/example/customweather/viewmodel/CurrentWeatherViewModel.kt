package com.example.customweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customweather.api.CurrentWeatherDataFetcher
import com.example.customweather.statistics.CurrentWeatherStatistics
import com.example.customweather.statistics.CurrentWeatherProcessed
import kotlinx.coroutines.launch

class CurrentWeatherViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<CurrentWeatherProcessed>()
    val weatherData: LiveData<CurrentWeatherProcessed> get() = _weatherData

    private val dataFetcher = CurrentWeatherDataFetcher()
    private val statisticsProcessor = CurrentWeatherStatistics()

    fun fetchWeatherData(location: String) {
        viewModelScope.launch {
            val data = dataFetcher.fetchCurrentWeatherData(location)
            val processedData = statisticsProcessor.processCurrentWeatherData(data)
            _weatherData.postValue(processedData)
        }
    }
}
