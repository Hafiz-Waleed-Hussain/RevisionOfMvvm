package com.uwantolearn.rapidapiassignment.feature.home

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.*
import androidx.paging.PagedList
import com.uwantolearn.rapidapiassignment.data.repositories.RapidAPIRepository
import com.uwantolearn.rapidapiassignment.data.repositories.Result
import com.uwantolearn.rapidapiassignment.model.RapidImage
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: RapidAPIRepository) : ViewModel() {
    private val query = MutableLiveData<String>()
    private val repoResult: LiveData<Result> = map(query, repo::loadImages)

    val images: LiveData<PagedList<RapidImage>> = switchMap(repoResult) { it.success }
    val error: LiveData<Throwable> = switchMap(repoResult) { it.error }

    fun searchImage(queryString: String) {
        query.postValue(queryString)
    }

    override fun onCleared() {
        repo.compositeDisposable.clear()
        super.onCleared()
    }
}