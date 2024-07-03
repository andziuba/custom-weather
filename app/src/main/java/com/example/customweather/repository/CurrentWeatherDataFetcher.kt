package com.example.customweather.repository

import com.example.customweather.Config
import com.example.customweather.api.ApiService
import com.example.customweather.api.data.WeatherData
import com.example.customweather.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CurrentWeatherDataFetcher {

    private val openWeatherApiService: ApiService = RetrofitInstance.openWeatherRetrofit.create(ApiService::class.java)
    private val weatherApiService: ApiService = RetrofitInstance.weatherApiRetrofit.create(ApiService::class.java)
    private val weatherBitApiService: ApiService = RetrofitInstance.weatherBitRetrofit.create(ApiService::class.java)
    private val tomorrowIoApiService: ApiService = RetrofitInstance.tomorrowIoRetrofit.create(ApiService::class.java)
    private val accuWeatherApiService: ApiService = RetrofitInstance.accuWeatherRetrofit.create(ApiService::class.java)

    suspend fun fetchCurrentWeatherData(location: String): List<WeatherData> = withContext(Dispatchers.IO) {
        val weatherDataList = mutableListOf<WeatherData>()

        try {
            // OpenWeather
            val openWeatherResponse = openWeatherApiService.getOpenWeather(location, Config.OPENWEATHER_API_KEY)
            if (openWeatherResponse.isSuccessful) {
                openWeatherResponse.body()?.let {
                    weatherDataList.add(it)
                }
            }

            // WeatherAPI
            val weatherApiResponse = weatherApiService.getWeatherApi(Config.WEATHERAPI_API_KEY, location)
            if (weatherApiResponse.isSuccessful) {
                weatherApiResponse.body()?.let {
                    weatherDataList.add(it)
                }
            }

            // WeatherBit
            val weatherBitResponse = weatherBitApiService.getWeatherBit(Config.WEATHERBIT_API_KEY, location)
            if (weatherBitResponse.isSuccessful) {
                weatherBitResponse.body()?.let {
                    weatherDataList.add(it)
                }
            }

            // Tomorrow.io
            val tomorrowIoResponse = tomorrowIoApiService.getTomorrowIo(location, Config.TOMORROWIO_API_KEY)
            if (tomorrowIoResponse.isSuccessful) {
                tomorrowIoResponse.body()?.let {
                    weatherDataList.add(it)
                }
            }

            // AccuWeather
            val accuWeatherLocationResponse = accuWeatherApiService.getAccuWeatherLocationKey(Config.ACCUWEATHER_API_KEY, location)
            if (accuWeatherLocationResponse.isSuccessful) {
                val locationKey = accuWeatherLocationResponse.body()?.firstOrNull()?.key
                if (locationKey != null) {
                    val accuWeatherResponse = accuWeatherApiService.getAccuWeather(
                        locationKey,
                        Config.ACCUWEATHER_API_KEY,
                        language = "en-us"
                    )
                    if (accuWeatherResponse.isSuccessful) {
                        accuWeatherResponse.body()?.let {
                            weatherDataList.add(it)
                        }
                    }
                }
            }

        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return@withContext weatherDataList
    }
}
