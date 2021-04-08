package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelClass(val myRepository: WeatherRepository) : ViewModel() {
    //    val weatherData = MutableLiveData<String>().apply { value = "test" }
//    val liveData: LiveData<String> = weatherData
    private val _weatherData = MutableStateFlow("test")
    val weatherData: StateFlow<String> = _weatherData
    private val appIdCoCity: String = "20b6511f63aa7fdecfdeb128049e9f7a"

    //    callback: (str: String) -> Unit
    fun updateApplyData(str: String) {
//        weatherData.value = str
        viewModelScope.launch { _weatherData.emit(str) }
    }

    fun fetchWeatherDataByCity(
        cityName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                myRepository.serviceCallWithCity(cityName, appIdCoCity)
            _weatherData.emit(result)
        }
    }

//    fun fetchWeatherDataByCoordinates(
//        latCo: String,
//        lonCo: String,
//        AppIdCo: String,
//        callback: (str: String) -> Unit
//    ) {
//        myRepository.serviceCall(latCo, lonCo, AppIdCo, callback)
//    }
}
