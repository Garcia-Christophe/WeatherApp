package com.example.papameteo.domain.model

import com.example.papameteo.data.repository.WeatherRepositoryInterface

class WeatherDetailsViewModel(repository: WeatherRepositoryInterface):WeatherViewModel(repository)
