package com.hackerstreet.stemup.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "termins")
class DictEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "catID")
    val categoryId: Int,
    @ColumnInfo(name = "termin")
    val term: String,
    @ColumnInfo(name = "description")
    val description: String,
)