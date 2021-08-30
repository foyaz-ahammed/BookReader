package com.orangetheory.book.global

import android.app.Application
import com.orangetheory.book.modules.networkModule
import com.orangetheory.book.modules.repositoryModule
import com.orangetheory.book.modules.viewModelModule
import org.koin.core.context.startKoin

class BookApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}