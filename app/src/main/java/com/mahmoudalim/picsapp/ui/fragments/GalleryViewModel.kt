package com.mahmoudalim.picsapp.ui.fragments

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.mahmoudalim.picsapp.data.repository.UnsplashRepo
import com.mahmoudalim.picsapp.util.constants.Companion.CURRENT_QUERY
import com.mahmoudalim.picsapp.util.constants.Companion.DEFAULT_QUERY

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepo,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = currentQuery.switchMap {
        repository.getSearchResult(it).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }
}