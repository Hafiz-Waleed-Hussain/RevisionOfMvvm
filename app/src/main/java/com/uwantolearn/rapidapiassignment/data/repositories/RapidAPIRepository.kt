package com.uwantolearn.rapidapiassignment.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.uwantolearn.rapidapiassignment.data.RapidApiBoundaryCallback
import com.uwantolearn.rapidapiassignment.data.source.remote.RemoteRapidApi
import com.uwantolearn.rapidapiassignment.database.LocalRapidApi
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RapidAPIRepository @Inject constructor(
    private val remote: RemoteRapidApi,
    private val local: LocalRapidApi
) {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loadImages(query: String): Result {

        val factory = local.imagesAgainstQuery(query)

        val boundaryCallback = RapidApiBoundaryCallback(query, remote, local, compositeDisposable)
        val error = boundaryCallback.error

        val data = LivePagedListBuilder(factory, 20)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return Result(data, error)
    }
}

data class Result(
    val success: LiveData<PagedList<RapidImagesAgainstQuery>>,
    val error: LiveData<Throwable>
)