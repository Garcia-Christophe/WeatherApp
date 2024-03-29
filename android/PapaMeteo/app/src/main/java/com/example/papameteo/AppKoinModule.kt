package com.example.papameteo

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import com.example.papameteo.data.api.OpenWeatherApi
import com.example.papameteo.data.repository.WeatherRepository
import com.example.papameteo.data.repository.WeatherRepositoryInterface
import com.example.papameteo.domain.model.FavViewModel
import com.example.papameteo.domain.model.LocationViewModel
import com.example.papameteo.domain.model.UserPreferencesRepositoryImpl
import com.example.papameteo.domain.model.UserPreferencesRepositoryInterface
import com.example.papameteo.domain.model.WeatherDetailsViewModel
import com.example.papameteo.domain.model.WeatherViewModel
import com.example.papameteo.other.WeatherRoomDb
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private inline val requireApplication
    get() = WeatherApplication.instance ?: error("Missing call: initWith(application)")

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

val database by lazy { WeatherRoomDb.getDatabase(requireApplication) }

val appModule = module {
    single<String>(named("weather_api_key")) { "888f70e84a4d7e44f3c0d4870c926e9d" }
    viewModel { WeatherViewModel(repository = get()) }
    viewModel { WeatherDetailsViewModel(repository = get()) }
    single<FusedLocationProviderClient> { LocationServices.getFusedLocationProviderClient(requireApplication.applicationContext) }
    viewModel { LocationViewModel(client =  get()) }
    single<UserPreferencesRepositoryInterface> { UserPreferencesRepositoryImpl(requireApplication.dataStore) }
    viewModel { FavViewModel(userPreferences = get()) }
    single<WeatherRepositoryInterface> { WeatherRepository(get(), database.weatherDao()) }
}

val commonModule = module {
    single { Android.create() }
    single { createJson() }
    single { createHttpClient(get(), get(), enableNetworkLogs = true) }
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
    single<WeatherRepositoryInterface> { WeatherRepository(get(), database.weatherDao()) }
    single { OpenWeatherApi(get(), get(named("weather_api_key"))) }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json, enableNetworkLogs: Boolean) =
    HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.ALL
            }
        }
    }

class CustomHttpLogger() : Logger {
    override fun log(message: String) {
        Log.i("CustomHttpLogger", "message : $message")
    }
}