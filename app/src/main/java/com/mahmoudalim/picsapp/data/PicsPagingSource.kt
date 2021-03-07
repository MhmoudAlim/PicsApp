package com.mahmoudalim.picsapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mahmoudalim.picsapp.api.UnsplashApi
import com.mahmoudalim.picsapp.data.models.UnsplashPhoto
import com.mahmoudalim.picsapp.util.constants.Companion.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class PicsPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        TODO("Not yet implemented")
    }


}