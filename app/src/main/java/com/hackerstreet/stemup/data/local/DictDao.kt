package com.hackerstreet.stemup.data.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query


@Dao
interface DictDao {

    @Query("SELECT * FROM termins WHERE catID = :categoryType  ORDER by termin ASC ")
    fun getAllTerms(categoryType: Int): Cursor

    @Query("Select * from termins where (termin like :query) and (catID = :categoryType ) ORDER by termin ASC")
    fun getSearchList(query: String, categoryType: Int): Cursor

    @Query("SELECT * FROM termins where catID = :categoryType ORDER BY RANDOM() LIMIT 4")
    fun getRandomTermsList(categoryType: Int): List<DictEntity>

    @Query("SELECT * FROM math WHERE id BETWEEN :start AND :end LIMIT 5")
    fun getMathLesson(start: Int, end: Int): List<MathEntity>

    @Query("SELECT * FROM engine WHERE id BETWEEN :start AND :end LIMIT 5")
    fun getEngineLesson(start: Int, end: Int): List<EnginEntity>

    @Query("SELECT * FROM sciense WHERE id BETWEEN :start AND :end LIMIT 5")
    fun getScienseLesson(start: Int, end: Int): List<ScienceEntity>

    @Query("SELECT * FROM tech WHERE id BETWEEN :start AND :end LIMIT 5")
    fun getTechLesson(start: Int, end: Int): List<TechEntity>

    @Query("SELECT * FROM math WHERE id % 5 = 0")
    fun getMathRoadMap(): List<MathEntity>

    @Query("SELECT * FROM tech WHERE id % 5 = 0")
    fun getTechRoadMap(): List<TechEntity>

    @Query("SELECT * FROM sciense WHERE id % 5 = 0")
    fun getScienseRoadMap(): List<ScienceEntity>

    @Query("SELECT * FROM engine WHERE id % 5 = 0")
    fun getEngineRoadMap(): List<EnginEntity>

    @Query("update math set  finished = :state  where id= :currentId")
    fun updateMathTest(currentId: Int, state: Int)

    @Query("update tech set  finished = :state  where id= :currentId")
    fun updateTechTest(currentId: Int, state: Int)

    @Query("update engine set  finished = :state  where id= :currentId")
    fun updateEngineTest(currentId: Int, state: Int)

    @Query("update sciense set  finished = :state  where id= :currentId")
    fun updateScienseTest(currentId: Int, state: Int)


}