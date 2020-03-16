package com.uwantolearn.rapidapiassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "images",
    foreignKeys = [ForeignKey(
        entity = RapidImageQuery::class,
        parentColumns = ["query"],
        childColumns = ["againstQuery"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RapidImage(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @field:SerializedName("url") val url: String = "",
    @field:SerializedName("width") val width: Int = 0,
    @field:SerializedName("height") val height: Int = 0,
    @field:SerializedName("thumbnail") val thumbnail: String = "",
    @field:SerializedName("thumbnailHeight") val thumbnailHeight: Int = 0,
    @field:SerializedName("thumbnailWidth") val thumbnailWidth: Int = 0,
    @field:SerializedName("base64Encoding") val base64Encoding: String? = null,
    @ColumnInfo(index = true) var againstQuery: String
)

