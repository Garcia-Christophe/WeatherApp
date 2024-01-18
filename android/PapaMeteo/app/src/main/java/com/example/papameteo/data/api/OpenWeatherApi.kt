package com.example.papameteo.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent

class OpenWeatherApi(
    private val client: HttpClient,
    private val apiKey : String,
    private var baseUrl: String = "https://api.openweathermap.org/data/2.5/forecast",
) : KoinComponent {

    suspend fun fetchWeather(city: String) : WeatherResultDto{
        return client.get("$baseUrl") {
            url {
                parameters.append("q", "$city, FR")
                parameters.append("APPID", apiKey)
                parameters.append("units", "metric")
            }
        }.body<WeatherResultDto>()
    }

    suspend fun fetchWeather(lat: Double, lon : Double) : WeatherResultDto{
        return client.get("$baseUrl") {
            url {
                parameters.append("lat", "$lat")
                parameters.append("lon", "$lon")
                parameters.append("APPID", apiKey)
                parameters.append("units", "metric")
            }
        }.body<WeatherResultDto>()
    }
}