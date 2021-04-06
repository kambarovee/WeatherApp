package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var weatherData: TextView? = null
    private var editText: EditText? = null
    private var AlmatyData = CitiesData(
        "https://api.openweathermap.org/",
        "20b6511f63aa7fdecfdeb128049e9f7a",
        "43.222015",
        "76.851250"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherData = findViewById(R.id.textView)

        editText = findViewById(R.id.edit_text)
        findViewById<View>(R.id.button).setOnClickListener {
            getCurrentData()
        }
    }

    private fun getCurrentData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(AlmatyData.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(AlmatyData.lat, AlmatyData.lon, AlmatyData.AppId)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: ${weatherResponse.sys!!.country} \n" +
                        "City: ${editText!!.text} \n" +
                        "Temperature: ${
                        (weatherResponse.main!!.temp - 273).toString().substring(0, 3)
                        }C \n" +
                        "Max Temperature: ${
                        (weatherResponse.main!!.temp_max - 273).toString().substring(0, 3)
                        }C \n" +
                        "Min Temperature: ${
                        (weatherResponse.main!!.temp_min - 273).toString().substring(0, 3)
                        }C \n" +
                        "Pressure: ${weatherResponse.main!!.pressure} \n" +
                        "Humidity: ${weatherResponse.main!!.humidity}"

                    weatherData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Please, check your Internet connection! ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
