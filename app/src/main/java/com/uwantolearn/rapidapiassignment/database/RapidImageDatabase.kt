package com.uwantolearn.rapidapiassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uwantolearn.rapidapiassignment.database.dao.RapidImageDAO
import com.uwantolearn.rapidapiassignment.model.RapidImage

@Database(entities = [RapidImage::class], version = 1)
abstract class RapidImageDatabase : RoomDatabase() {
    abstract fun imagesDAO(): RapidImageDAO

    companion object {
        @Volatile
        private var INSTANCE: RapidImageDatabase? = null

        fun getInstance(context: Context): RapidImageDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RapidImageDatabase::class.java,
                "RapidAPI.db"
            ).build()
    }
}