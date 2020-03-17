package com.uwantolearn.api.retrofit.service

import com.uwantolearn.rapidapiassignment.model.RapidImage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RapidAPIRetrofitService {
    @GET("Search/ImageSearchAPI?")
    fun loadImages(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("autoCorrect") autoCorrect: Boolean = false,
        @Query("safeSearch") safeSearch: Boolean = false
    ): Observable<List<RapidImage>>
}