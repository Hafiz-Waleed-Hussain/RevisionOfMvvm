package com.uwantolearn.rapidapiassignment.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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
    fun loadImages(queryString: String): DataSource.Factory<Int, RapidImagesAgainstQuery>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rapidImageEntity: List<RapidImage>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rapidQuery: RapidImageQuery)

    @Delete
    fun delete(vararg rapidImageEntity: RapidImage)

}