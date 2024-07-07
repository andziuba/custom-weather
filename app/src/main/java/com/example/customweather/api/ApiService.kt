package com.example.customweather.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.customweather.api.data.*

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getOpenWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<OpenWeatherData>

    @GET("v1/current.json")
    suspend fun getWeatherApi(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): Response<WeatherApiData>

    @GET("current")
    suspend fun getWeatherBit(
        @Query("key") apiKey: String,
        @Query("city") location: String
    ): Response<WeatherBitData>

    @GET("v4/timelines")
    suspend fun getTomorrowIo(
        @Query("location") location: String,
        @Query("fields") fields: String,
        @Query("timesteps") timesteps: String,
        @Query("units") units: String,
        @Query("apikey") apiKey: String
    ): Response<TomorrowIoData>

    @GET("locations/v1/cities/search")
    suspend fun getAccuWeatherLocationKey(
        @Query("apikey") apiKey: String,
        @Query("q") location: String
    ): Response<List<AccuWeatherLocationResponse>>

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getAccuWeather(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String,
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean = true,
        @Query("language") language: String
    ): Response<List<AccuWeatherData>>
}
