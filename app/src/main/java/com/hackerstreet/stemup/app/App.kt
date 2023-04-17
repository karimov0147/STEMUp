package com.hackerstreet.stemup.app

import android.app.Application
import com.hackerstreet.stemup.data.local.AppDatabase
import com.hackerstreet.stemup.data.storage.LocaleStorage
import com.hackerstreet.stemup.domain.repository.Repository

class App : Application() {
    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        database
        LocaleStorage.initContext(this.applicationContext)
        Repository.initRepository()
    }
}