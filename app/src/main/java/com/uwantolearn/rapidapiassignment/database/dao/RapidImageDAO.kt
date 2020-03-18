package com.uwantolearn.rapidapiassignment.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.uwantolearn.rapidapiassignment.model.RapidImage

@Dao
interface RapidImageDAO {

    @Transaction
    @Query("SELECT * FROM image WHERE againstQuery=:queryString")
    fun loadImages(queryString: String): DataSource.Factory<Int, RapidImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rapidImageEntity: List<RapidImage>)

    @Delete
    fun delete(vararg rapidImageEntity: RapidImage)

}