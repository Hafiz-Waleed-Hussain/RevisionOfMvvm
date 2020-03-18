package com.uwantolearn.rapidapiassignment.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class RapidImagesAgainstQuery(
    @Embedded val query: RapidImageQuery,
    @Relation(
        parentColumn = "query",
        entityColumn = "againstQuery"
    )
    val image: RapidImage
)