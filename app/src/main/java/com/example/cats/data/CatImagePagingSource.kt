package com.example.cats.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.cats.CatImageModel
import com.example.cats.api.CatApiService
import com.example.cats.data.CatImagesRepository.Companion.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CatImagePagingSource(val CatApiService: CatApiService) :
    PagingSource<Int, CatImageModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImageModel> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = CatApiService.getCatImages(page, params.loadSize)
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}