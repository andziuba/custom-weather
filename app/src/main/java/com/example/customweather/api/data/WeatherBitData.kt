package com.example.customweather.api.data

data class WeatherBitData(
    val data: List<Data>
) : WeatherData {
    override val currentTemperature: Double
        get() = data[0].temp

    override val feelsLikeCurrentTemperature: Double
        get() = data[0].app_temp

    override val uvIndex: Double
        get() = data[0].uv.toDouble()
}

data class Data(
    val temp: Double,
    val app_temp: Double,
    val uv: Int
)