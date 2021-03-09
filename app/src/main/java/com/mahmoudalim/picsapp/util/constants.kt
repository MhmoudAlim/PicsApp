package com.mahmoudalim.picsapp.util

import com.mahmoudalim.picsapp.BuildConfig

class constants {
    companion object {
        const val APP_NAME = "PicsApp"
        const val API_KEY = BuildConfig.UNSPLASH_ACCESS_KEY
        const val BASE_URL = "https://api.unsplash.com/"
        const val STARTING_PAGE_INDEX = 1
        const val DEFAULT_QUERY = "dogs"
        const val CURRENT_QUERY = "searchKey"
    }
}