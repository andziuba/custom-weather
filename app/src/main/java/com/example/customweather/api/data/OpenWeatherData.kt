package com.example.customweather.api.data

data class OpenWeatherData(
    val main: Main,
    val uv: Double? = null // not provided
) : WeatherData {
    override val temperature: Double
        get() = main.temp

    override val feelsLikeCurrentTemperature: Double
        get() = main.feels_like

    override val uvIndex: Double?
        get() = uv
}

data class Main(
    val temp: Double,
    val feels_like: Double
)
