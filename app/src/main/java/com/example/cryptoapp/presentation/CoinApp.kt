package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.workers.CoinWorkerFactory
import com.example.cryptoapp.di.DaggerAppComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory).build()
    }

    override fun onCreate() {
        component.inject(this)

        super.onCreate()
    }
}