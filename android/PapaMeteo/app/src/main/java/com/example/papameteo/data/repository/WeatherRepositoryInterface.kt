package com.example.papameteo.data.repository

import com.example.papameteo.data.core.Resource
import com.example.papameteo.domain.model.WeatherResultDomain
import kotlinx.coroutines.flow.Flow

interface WeatherRepositoryInterface {
    suspend fun fetchWeather(city: String): Flow<Resource<WeatherResultDomain?>>
    suspend fun fetchWeather(lat: Double, lon: Double): Flow<Resource<WeatherResultDomain?>>
}