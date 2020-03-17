package com.uwantolearn.rapidapiassignment.api.source

import com.uwantolearn.rapidapiassignment.model.RapidImage
import io.reactivex.Observable

interface RapidApiDataSource {
    fun loadImages(query: String, pageNumber: Int): Observable<List<RapidImage>>
}