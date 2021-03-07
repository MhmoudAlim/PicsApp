package com.mahmoudalim.picsapp.api

import com.mahmoudalim.picsapp.data.models.UnsplashResponse
import com.mahmoudalim.picsapp.util.constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Accept-Version: v1",
        "Authorization: Client-ID $API_KEY")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UnsplashResponse
}