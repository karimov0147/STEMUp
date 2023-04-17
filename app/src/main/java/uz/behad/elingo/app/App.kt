package uz.behad.elingo.app

import android.app.Application
import uz.behad.elingo.data.local.AppDatabase
import uz.behad.elingo.data.storage.LocaleStorage
import uz.behad.elingo.domain.repository.Repository

class App : Application() {
    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        database
        LocaleStorage.initContext(this.applicationContext)
        Repository.initRepository()
    }
}