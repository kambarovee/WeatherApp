package com.example.weatherapp

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("sys") var sys: Sys,
    @SerializedName("main") var main: Main
)

data class Main(
    @SerializedName("temp")
    var temp: Float = 0.0f,
    @SerializedName("humidity")
    var humidity: Float = 0.0f,
    @SerializedName("pressure")
    var pressure: Float = 0.0f,
    @SerializedName("temp_min")
    var temp_min: Float = 0.0f,
    @SerializedName("temp_max")
    var temp_max: Float = 0.0f,
    @SerializedName("name")
    var name: String
)

data class Sys(
    @SerializedName("country")
    var country: String
)
