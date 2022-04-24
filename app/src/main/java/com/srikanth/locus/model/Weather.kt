package com.srikanth.locus.model


data class Weather(
    val cod: Int,
    val weather: List<WeatherList>,
    val main: Temperature
)

data class WeatherList(
    val main: String,
    val description: String
)

data class Temperature(
    val temp: String
)