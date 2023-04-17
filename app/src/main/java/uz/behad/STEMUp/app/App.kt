package uz.behad.STEMUp.app

import android.app.Application
import uz.behad.STEMUp.data.local.AppDatabase
import uz.behad.STEMUp.data.storage.LocaleStorage
import uz.behad.STEMUp.domain.repository.Repository

class App : Application() {
    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        database
        LocaleStorage.initContext(this.applicationContext)
        Repository.initRepository()
    }
}