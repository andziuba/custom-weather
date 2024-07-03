package com.example.customweather.api.data

data class WeatherApiData(
    val current: Current
) : WeatherData {
    override val temperature: Double
        get() = current.temp_c

    override val feelsLikeCurrentTemperature: Double
        get() = current.feelslike_c

    override val uvIndex: Double
        get() = current.uv
}

data class Current(
    val temp_c: Double,
    val feelslike_c: Double,
    val uv: Double
)