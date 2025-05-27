package com.meet.movieexplorer

import android.app.Application
import com.meet.movieexplorer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieExplorerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieExplorerApp)
            modules(appModule)
        }
    }
}