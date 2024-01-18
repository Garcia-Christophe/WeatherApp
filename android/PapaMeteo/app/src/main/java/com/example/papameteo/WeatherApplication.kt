package com.example.papameteo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class WeatherApplication : Application() {

    companion object {
        var instance: WeatherApplication? = null
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(appModule)
            modules(commonModule)
        }
    }
}