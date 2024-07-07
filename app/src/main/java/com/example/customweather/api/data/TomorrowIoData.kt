package com.example.customweather.api.data

data class TomorrowIoData(
    val data: Timelines
) : WeatherData {
    override val currentTemperature: Double
        get() = data.timelines[0].intervals[0].values.temperature

    override val feelsLikeCurrentTemperature: Double
        get() = data.timelines[0].intervals[0].values.temperatureApparent

    override val uvIndex: Double
        get() = data.timelines[0].intervals[0].values.uv
}

data class Timelines(
    val timelines: List<Timeline>
)

data class Timeline(
    val intervals: List<Interval>
)

data class Interval(
    val values: Values
)

data class Values(
    val temperature: Double,
    val temperatureApparent: Double,
    val uv: Double
)
