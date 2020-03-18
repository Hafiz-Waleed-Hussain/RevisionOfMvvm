package com.uwantolearn.rapidapiassignment.view.home

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.uwantolearn.rapidapiassignment.data.repositories.RapidAPIRepository
import com.uwantolearn.rapidapiassignment.data.repositories.Result
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: RapidAPIRepository
) : ViewModel() {



    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<Result> = Transformations.map(queryLiveData) {
        repo.loadImages(it)
    }

    val images: LiveData<PagedList<RapidImagesAgainstQuery>> = Transformations.switchMap(repoResult) { it -> it.success }
    val networkErrors: LiveData<Throwable> = Transformations.switchMap(repoResult) { it ->
        it.error
    }

    fun searchImage(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    fun lastQueryValue(): String? = queryLiveData.value
}