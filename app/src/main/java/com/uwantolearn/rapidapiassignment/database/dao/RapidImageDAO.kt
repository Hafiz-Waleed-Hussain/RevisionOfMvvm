package com.uwantolearn.rapidapiassignment.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImageQuery
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface RapidImageDAO {

    @Transaction
    @Query("SELECT * FROM imagesQuery WHERE query=:queryString")
    fun loadImages(queryString: String): LiveData<List<RapidImagesAgainstQuery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg rapidImageEntity: RapidImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rapidQuery: RapidImageQuery)

    @Delete
    fun delete(vararg rapidImageEntity: RapidImage)

}