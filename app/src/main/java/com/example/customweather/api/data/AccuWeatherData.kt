package com.example.customweather.api.data

data class AccuWeatherData(
    val Temperature: Temperature,
    val RealFeelTemperatureShade: RealFeelTemperatureShade,
    val UVIndex: Int
) : WeatherData {
    override val currentTemperature: Double
        get() = Temperature.Metric.Value

    override val feelsLikeCurrentTemperature: Double
        get() = RealFeelTemperatureShade.Metric.Value

    override val uvIndex: Double
        get() = UVIndex.toDouble()
}

data class Metric(
    val Value: Double
)

data class Temperature(
    val Metric: Metric
)

data class RealFeelTemperatureShade(
    val Metric: Metric
)


