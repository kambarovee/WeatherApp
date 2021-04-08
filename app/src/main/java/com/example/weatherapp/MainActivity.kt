package com.example.weatherapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var weatherData: TextView? = null
    private var editText: EditText? = null

    //    val repository: WeatherRepository by inject()
    val viewModel: ViewModelClass by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        weatherData = binding.textView

        editText = binding.editText
        binding.button.setOnClickListener {
            getCurrentData(editText?.text.toString())
        }

        initObserver()
    }

    private fun initObserver() {
//        viewModel.liveData.observe(this, ::updateUI)

        lifecycleScope.launchWhenStarted { viewModel.stateFLow.collect(::updateUI) }
    }

    fun updateUI(string: String) {
        weatherData?.text = string
    }

    private fun getCurrentData(cityName: String) {

        viewModel.fetchWeatherDataByCity(
            cityName
        )
//        repository.serviceCallWithCity(
//            cityName, "20b6511f63aa7fdecfdeb128049e9f7a",
//            ::updateUI
//        )

//        repository.serviceCall(
//            "43.222015",
//            "76.851250",
//            "20b6511f63aa7fdecfdeb128049e9f7a",
//            { str ->
//                weatherData?.text = str
//            }
//        )
    }
}
