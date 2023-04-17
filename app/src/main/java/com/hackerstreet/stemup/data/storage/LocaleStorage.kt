package com.hackerstreet.stemup.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.hackerstreet.stemup.data.local.EnginEntity
import com.hackerstreet.stemup.data.local.MathEntity
import com.hackerstreet.stemup.data.local.ScienceEntity
import com.hackerstreet.stemup.data.local.TechEntity
import com.hackerstreet.stemup.data.models.LessonModel

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