package com.ned.disneycharacter.ui

import android.app.Application
import com.ned.core.injection.databaseModule
import com.ned.core.injection.networkModule
import com.ned.core.injection.repositoryModule
import com.ned.disneycharacter.injection.useCaseModule
import com.ned.disneycharacter.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MainApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}