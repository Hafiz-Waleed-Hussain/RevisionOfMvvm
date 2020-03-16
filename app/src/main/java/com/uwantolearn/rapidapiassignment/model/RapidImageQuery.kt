package com.uwantolearn.rapidapiassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imagesQuery")
data class RapidImageQuery(
    @PrimaryKey val query: String
)