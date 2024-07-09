package com.example.customweather.api

import android.util.Log
import com.example.customweather.Config
import com.example.customweather.api.data.WeatherData
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
                    Log.d("WeatherDataFetcher", "OpenWeather: ${it.currentTemperature}")
                }
            }

            // WeatherAPI
            val weatherApiResponse = weatherApiService.getWeatherApi(Config.WEATHERAPI_API_KEY, location)
            if (weatherApiResponse.isSuccessful) {
                weatherApiResponse.body()?.let {
                    weatherDataList.add(it)
                    Log.d("WeatherDataFetcher", "WeatherAPI: ${it.currentTemperature}, ${it.uvIndex}")
                }
            }

            // WeatherBit
            val weatherBitResponse = weatherBitApiService.getWeatherBit(Config.WEATHERBIT_API_KEY, location)
            if (weatherBitResponse.isSuccessful) {
                weatherBitResponse.body()?.let {
                    weatherDataList.add(it)
                    Log.d("WeatherDataFetcher", "WeatherBit: ${it.currentTemperature}, ${it.uvIndex}")
                }
            }

            // Tomorrow.io
            val tomorrowIoResponse = tomorrowIoApiService.getTomorrowIo(
                location, "temperature,temperatureApparent,uvIndex", "1h", "metric", Config.TOMORROWIO_API_KEY)
            if (tomorrowIoResponse.isSuccessful) {
                tomorrowIoResponse.body()?.let {
                    weatherDataList.add(it)
                    Log.d("WeatherDataFetcher", "Tomorrow.io: ${it.currentTemperature}, ${it.uvIndex}")
                }
            }

            // AccuWeather
            val accuWeatherLocationResponse = accuWeatherApiService.getAccuWeatherLocationKey(Config.ACCUWEATHER_API_KEY, location)
            if (accuWeatherLocationResponse.isSuccessful) {
                val locations = accuWeatherLocationResponse.body()
                if (locations != null && locations.isNotEmpty()) {
                    val locationKey = locations[0].Key
                    val accuWeatherResponse = accuWeatherApiService.getAccuWeather(
                        locationKey,
                        Config.ACCUWEATHER_API_KEY,
                        language = "en-us"
                    )

                    if (accuWeatherResponse.isSuccessful) {
                        val accuWeatherDataList = accuWeatherResponse.body()
                        if (accuWeatherDataList != null && accuWeatherDataList.isNotEmpty()) {
                            weatherDataList.addAll(accuWeatherDataList)
                            Log.d("WeatherDataFetcher", "AccuWeather Temp: ${accuWeatherDataList[0].currentTemperature}, ${accuWeatherDataList[0].uvIndex}, Location Key: $locationKey")
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
