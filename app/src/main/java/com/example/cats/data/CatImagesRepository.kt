package com.example.cats.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.example.cats.CatImageModel
import com.example.cats.api.CatApiService
import com.example.cats.api.Remote
import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class CatImagesRepository(
    val CatApiService: CatApiService = Remote.injectCatApiService(),
) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20

        fun getInstance() = CatImagesRepository()
    }

    fun letCatImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<CatImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory =  {CatImagePagingSource(CatApiService) }
        ).liveData
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

}