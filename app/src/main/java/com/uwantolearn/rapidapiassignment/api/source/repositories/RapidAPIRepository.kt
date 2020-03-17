package com.uwantolearn.rapidapiassignment.api.source.repositories

import com.uwantolearn.api.retrofit.service.RapidAPIRetrofitService
import com.uwantolearn.rapidapiassignment.api.source.RapidApiDataSource
import com.uwantolearn.rapidapiassignment.api.source.remote.RemoteRapidApiDataDataSource
import com.uwantolearn.rapidapiassignment.model.RapidImage
import io.reactivex.Observable
import javax.inject.Inject

internal class RapidAPIRepository @Inject constructor(private val remote: RemoteRapidApiDataDataSource) :
    RapidApiDataSource {

    override fun loadImages(query: String, pageNumber: Int): Observable<List<RapidImage>> {
        return remote.loadImages(query, pageNumber)
    }
}