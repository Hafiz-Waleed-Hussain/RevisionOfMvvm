package com.uwantolearn.rapidapiassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uwantolearn.rapidapiassignment.database.dao.RapidImageDAO
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImageQuery
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery

@Database(
    entities = [RapidImage::class, RapidImageQuery::class],
    version = 1
)
abstract class RapidImageDatabase : RoomDatabase() {
    abstract fun images(): RapidImageDAO
}