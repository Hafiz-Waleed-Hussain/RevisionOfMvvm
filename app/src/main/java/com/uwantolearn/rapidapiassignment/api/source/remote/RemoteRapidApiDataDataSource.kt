package com.uwantolearn.rapidapiassignment.api.source.remote

import com.uwantolearn.api.retrofit.service.RapidAPIRetrofitService
import com.uwantolearn.rapidapiassignment.api.source.RapidApiDataSource
import com.uwantolearn.rapidapiassignment.model.RapidImage
import io.reactivex.Observable
import javax.inject.Inject

internal class RemoteRapidApiDataDataSource @Inject constructor(private val service: RapidAPIRetrofitService) :
    RapidApiDataSource {

    override fun loadImages(query: String, pageNumber: Int): Observable<List<RapidImage>> {
        return service.loadImages(query, pageNumber)
    }
}