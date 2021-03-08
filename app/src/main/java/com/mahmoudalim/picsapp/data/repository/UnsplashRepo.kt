package com.mahmoudalim.picsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mahmoudalim.picsapp.api.UnsplashApi
import com.mahmoudalim.picsapp.data.PicsPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepo @Inject constructor(
    private val unsplashApi: UnsplashApi
) {
    fun getSearchResult(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 50,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PicsPagingSource(unsplashApi, query) }
        ).liveData
}