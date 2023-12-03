package com.github.dach83.todo

import android.app.Application
import di.androidModule
import di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidApp)
            modules(commonModule, androidModule)
        }
    }
}
