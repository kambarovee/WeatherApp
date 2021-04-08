package com.example.weatherapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { ViewModelClass(get()) }
    single { WeatherRepository(get()) }
    single {
        provideRetrofit(get())
    }

    single { get<Retrofit>().create(WeatherService::class.java) }

    single { provideOKHttp() }
}

fun provideOKHttp(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    return client
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
