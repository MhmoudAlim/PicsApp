package com.mahmoudalim.picsapp.ui.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mahmoudalim.picsapp.data.repository.UnsplashRepo
import com.mahmoudalim.picsapp.util.constants.Companion.DEFAULT_QUERY

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepo
) : ViewModel() {

    private val currenQtuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currenQtuery.switchMap {
        repository.getSearchResult(it).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currenQtuery.value = query
    }
}