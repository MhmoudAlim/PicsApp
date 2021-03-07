package com.mahmoudalim.picsapp.data.repository

import com.mahmoudalim.picsapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepo @Inject constructor(
    private val unsplashApi: UnsplashApi
    ) {

}