package com.cj3dreams.fetchchicago

import android.app.Application
import com.cj3dreams.fetchchicago.di.dataSourceModule
import com.cj3dreams.fetchchicago.di.networkModule
import com.cj3dreams.fetchchicago.di.sortedLocalViewModel
import com.cj3dreams.fetchchicago.di.unsortedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, dataSourceModule,
                sortedLocalViewModel, unsortedViewModel)
        }
    }
}