package com.example.customweather.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getOpenWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<com.example.customweather.api.data.OpenWeatherData>

    @GET("v1/current.json")
    suspend fun getWeatherApi(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): Response<com.example.customweather.api.data.WeatherApiData>

    @GET("current")
    suspend fun getWeatherBit(
        @Query("key") apiKey: String,
        @Query("city") location: String
    ): Response<com.example.customweather.api.data.WeatherBitData>

    @GET("v4/weather/realtime")
    suspend fun getTomorrowIo(
        @Query("location") location: String,
        @Query("apikey") apiKey: String
    ): Response<com.example.customweather.api.data.TomorrowIoData>

    @GET("locations/v1/cities/search")
    suspend fun getAccuWeatherLocationKey(
        @Query("apikey") apiKey: String,
        @Query("q") location: String
    ): Response<List<com.example.customweather.api.data.AccuWeatherLocationResponse>>

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getAccuWeather(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String,
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean = true,
        @Query("language") language: String
    ): Response<com.example.customweather.api.data.AccuWeatherData>
}
