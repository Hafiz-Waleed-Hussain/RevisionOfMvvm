package com.uwantolearn.api.retrofit.service

import com.uwantolearn.rapidapiassignment.model.RapidImage
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RapidAPIRetrofitService {
    @GET("/api/Search/ImageSearchAPI")
    fun loadImages(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("autoCorrect") autoCorrect: Boolean = false,
        @Query("safeSearch") safeSearch: Boolean = false
    ): Single<List<RapidImage>>
}