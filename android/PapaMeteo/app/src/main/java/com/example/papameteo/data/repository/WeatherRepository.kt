package com.example.papameteo.data.repository

import com.example.papameteo.data.api.OpenWeatherApi
import com.example.papameteo.data.core.Resource
import com.example.papameteo.domain.model.WeatherResultDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository(private val api : OpenWeatherApi) : WeatherRepositoryInterface {
    override suspend fun fetchWeather(city: String): Flow<Resource<WeatherResultDomain?>> {
        return flow {
            emit(Resource.Loading())
            emit(Resource.Success(api.fetchWeather(city = city).toDomain()))
        }
    }

    override suspend fun fetchWeather(
        lat: Double,
        lon: Double
    ): Flow<Resource<WeatherResultDomain?>> {
        return flow {
            emit(Resource.Loading())
            emit(Resource.Success(api.fetchWeather(lat = lat, lon = lon).toDomain()))
        }
    }

}