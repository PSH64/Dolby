package kr.ac.shingu.dolby

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblMarker")
data class MarkerETY(
    @PrimaryKey
    val name:String,
    val latitude:Double,
    val longitude:Double
)
