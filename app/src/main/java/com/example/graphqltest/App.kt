package com.example.graphqltest

import android.app.Application
import com.example.graphqltest.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule
                )
            )
        }
    }
}