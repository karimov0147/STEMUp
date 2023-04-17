package uz.behad.STEMUp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "math")
class MathEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "termin")
    val termin: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "finished")
    val isFinished: Int,
)