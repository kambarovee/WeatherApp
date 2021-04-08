package com.example.weatherapp

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("APPID") app_id: String
    ): WeatherResponse

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherDataWithCity(
        @Query("q") city: String,
        @Query("APPID") app_id: String
    ): WeatherResponse
}
