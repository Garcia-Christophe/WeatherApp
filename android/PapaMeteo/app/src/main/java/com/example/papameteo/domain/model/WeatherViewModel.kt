package com.example.papameteo.domain.model

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papameteo.data.core.Status
import com.example.papameteo.data.repository.WeatherRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WeatherViewModelState(
    var city: WeatherCityDomain? = null,
    var first: WeatherItemDomain? = null,
    var items: List<WeatherItemDomain> = emptyList(),
    var isLoading: Boolean = false,
)

open class WeatherViewModel(private val repository: WeatherRepositoryInterface) :
    ViewModel() {

    private val _viewState = MutableStateFlow<WeatherViewModelState>(WeatherViewModelState())
    var viewState = _viewState.asStateFlow()

    fun cityChanged(value: String) = fetchWeather(value)

    fun locationChanged(value: Location) = fetchWeather(value)

    private fun fetchWeather(location: Location) = fetchWithFlow(null, location)

    private fun fetchWeather(city: String) = fetchWithFlow(city)

    private fun fetchWithFlow(city: String? = null, location: Location? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val flow = if (city != null) {
                repository.fetchWeather(city = city)
            } else {
                repository.fetchWeather(lat = location!!.latitude, lon = location.longitude)
            }

            flow.collect {
                _viewState.value = WeatherViewModelState(
                    city = it.data?.city,
                    first = it.data?.items?.firstOrNull(),
                    items = it.data?.items ?: emptyList(),
                    isLoading = it.status == Status.LOADING
                )
            }
        }
    }
}