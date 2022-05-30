package com.rasika.planets.application

import android.app.Application
import com.rasika.planets.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PlanetApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createTimber()
    }

    private fun createTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}