package com.uwantolearn.rapidapiassignment.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.uwantolearn.rapidapiassignment.data.source.remote.RemoteRapidApi
import com.uwantolearn.rapidapiassignment.database.LocalRapidApi
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery
import io.reactivex.disposables.CompositeDisposable

class RapidApiBoundaryCallback(
    private val query: String,
    private val remoteRapidApi: RemoteRapidApi,
    private val localRapidApi: LocalRapidApi,
    private val compositeDisposable: CompositeDisposable
) : PagedList.BoundaryCallback<RapidImagesAgainstQuery>() {

    private var lastDownloadedPageNumber = 1
    private var isDownloding = false

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    override fun onZeroItemsLoaded() {
        downloadDataAndSave(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: RapidImagesAgainstQuery) {
        downloadDataAndSave(query)
    }

    private fun downloadDataAndSave(query: String) {
        if (isDownloding) return
        remoteRapidApi.loadImages(query, lastDownloadedPageNumber)
            .doOnSubscribe { isDownloding = true }
            .doOnEvent { _, _ -> isDownloding = false }
            .subscribe(
                { data -> localRapidApi.insert(query, data) { lastDownloadedPageNumber++ } },
                { t -> _error.postValue(t) }
            )
            .let(compositeDisposable::add)
    }

}