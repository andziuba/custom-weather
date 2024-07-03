package com.example.customweather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun createRetrofitInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val openWeatherRetrofit: Retrofit by lazy {
        createRetrofitInstance("https://api.openweathermap.org/")
    }

    val weatherApiRetrofit: Retrofit by lazy {
        createRetrofitInstance("https://api.weatherapi.com/")
    }

    val weatherBitRetrofit: Retrofit by lazy {
        createRetrofitInstance("https://api.weatherbit.io/v2.0/")
    }

    val tomorrowIoRetrofit: Retrofit by lazy {
        createRetrofitInstance("https://api.tomorrow.io/")
    }

    val accuWeatherRetrofit: Retrofit by lazy {
        createRetrofitInstance("https://dataservice.accuweather.com/")
    }
}
