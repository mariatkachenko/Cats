package com.example.cats.ui

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import com.example.cats.data.CatImagesRepository
import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class CatViewModel (
    val repository: CatImagesRepository = CatImagesRepository.getInstance()
) : ViewModel() {

    fun fetchCatImages(): Flow<PagingData<String>> {
        return repository.letCatImagesFlow()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)
    }

    @ExperimentalCoroutinesApi
    fun fetchCatImagesObservable(): Observable<PagingData<String>> {
        return repository.letCatImagesObservable()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)
    }

    fun fetchCatImagesLiveData(): LiveData<PagingData<String>> {
        return repository.letCatImagesLiveData()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)
    }

}