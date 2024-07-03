package com.example.customweather.api.data

data class AccuWeatherData(
    val temp: Temp,
    val uv: Int? = null
) : WeatherData {
    override val temperature: Double
        get() = temp.metric.value

    override val feelsLikeCurrentTemperature: Double
        get() = temp.metric.feels_like

    override val uvIndex: Double?
        get() = uv?.toDouble()
}

data class Temp(
    val metric: Metric
)

data class Metric(
    val value: Double,
    val feels_like: Double
)