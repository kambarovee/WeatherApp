package com.example.weatherapp

class WeatherRepository(val service: WeatherService) {

    suspend fun serviceCallWithCity(
        cityName: String,
        AppIdCoCity: String
    ): String {
        val response = service.getCurrentWeatherDataWithCity(cityName, AppIdCoCity)

        val stringBuilder = "Country: ${response.sys.country} \n" +
            "City: $cityName \n" +
            "Temperature: ${
            (response.main.temp - 273).toString().substring(0, 3)
            }C \n" +
            "Max Temperature: ${
            (response.main.temp_max - 273).toString()
                .substring(0, 3)
            }C \n" +
            "Min Temperature: ${
            (response.main.temp_min - 273).toString()
                .substring(0, 3)
            }C \n" +
            "Pressure: ${response.main.pressure} \n" +
            "Humidity: ${response.main.humidity}"

        return stringBuilder
    }
}

//
// suspend fun serviceCall(
//    latCo: String,
//    lonCo: String,
//    AppIdCo: String,
//    callback: (str: String) -> Unit
// ) {
//    val call = service.getCurrentWeatherData(latCo, lonCo, AppIdCo)
//    call.enqueue(
//        object : Callback<response> {
//            override fun onResponse(
//                call: Call<response>,
//                response: Response<response>
//            ) {
//                if (response.code() == 200) {
//                    val response = response.body()
//
//                    val stringBuilder = "Country: ${response?.sys?.country} \n" +
//                            "Temperature: ${
//                                (response?.main!!.temp - 273).toString().substring(0, 3)
//                            }C \n" +
//                            "Max Temperature: ${
//                                (response.main!!.temp_max - 273).toString()
//                                    .substring(0, 3)
//                            }C \n" +
//                            "Min Temperature: ${
//                                (response.main!!.temp_min - 273).toString()
//                                    .substring(0, 3)
//                            }C \n" +
//                            "Pressure: ${response.main!!.pressure} \n" +
//                            "Humidity: ${response.main!!.humidity}"
//
//                    callback(stringBuilder)
//                }
//            }
//
//            override fun onFailure(call: Call<response>, t: Throwable) {
//                val stringException = "${t.message}"
//                callback(stringException)
//            }
//        })
// }
