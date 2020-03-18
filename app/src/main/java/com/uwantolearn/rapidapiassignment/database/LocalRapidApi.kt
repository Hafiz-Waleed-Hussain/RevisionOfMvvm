package com.uwantolearn.rapidapiassignment.database

import androidx.paging.DataSource
import com.uwantolearn.rapidapiassignment.database.dao.RapidImageDAO
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImageQuery
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery
import com.uwantolearn.rapidapiassignment.utils.AppExecutor
import javax.inject.Inject

class LocalRapidApi @Inject constructor(
    private val dao: RapidImageDAO,
    private val appExecutor: AppExecutor
) {
    fun insert(query: String, data: List<RapidImage>, complete: () -> Unit) {
        appExecutor.ioExecutor().execute {
            RapidImageQuery(query).let(dao::insert)
            data.map { it.apply { againstQuery = query } }
                .let(dao::insert)
            complete()
        }
    }

    fun imagesAgainstQuery(query: String): DataSource.Factory<Int, RapidImagesAgainstQuery> {
        return dao.loadImages(query)
    }
}