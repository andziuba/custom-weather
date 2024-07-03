package com.example.customweather.api.data

data class TomorrowIoData(
    val values: Values
) : WeatherData {
    override val temperature: Double
        get() = values.temperature

    override val feelsLikeCurrentTemperature: Double
        get() = values.temperatureApparent

    override val uvIndex: Double
        get() = values.uvIndex.toDouble()
}

data class Values(
    val temperature: Double,
    val temperatureApparent: Double,
    val uvIndex: Int
)