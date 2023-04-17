package uz.behad.elingo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DictEntity::class, TechEntity::class, MathEntity::class, ScienceEntity::class, EnginEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dictDAO(): DictDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database"
                )
                    .createFromAsset("database.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

}