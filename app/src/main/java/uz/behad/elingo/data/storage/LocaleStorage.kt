package uz.behad.elingo.data.storage

import android.content.Context
import android.content.SharedPreferences
import uz.behad.elingo.data.local.EnginEntity
import uz.behad.elingo.data.local.MathEntity
import uz.behad.elingo.data.local.ScienceEntity
import uz.behad.elingo.data.local.TechEntity
import uz.behad.elingo.data.models.LessonModel

object LocaleStorage {


    private lateinit var sharedPreferences: SharedPreferences

    fun initContext(context: Context) {
        sharedPreferences = context.getSharedPreferences("category", Context.MODE_PRIVATE)
    }


    fun setCategory(value: Int) {
        sharedPreferences.edit().putInt("category", value).apply()
    }

    fun getCategory(): Int = sharedPreferences.getInt("category", 1)

    fun setPoints(value: Int) {
        sharedPreferences.edit().putInt("points", value).apply()
    }

    fun getPoints(): Int = sharedPreferences.getInt("points", 0)

    fun MathEntity.toLessonModel() = LessonModel(id, termin, description, isFinished)
    fun TechEntity.toLessonModel() = LessonModel(id, termin, description, isFinished)
    fun EnginEntity.toLessonModel() = LessonModel(id, termin, description, isFinished)
    fun ScienceEntity.toLessonModel() = LessonModel(id, termin, description, isFinished)


}