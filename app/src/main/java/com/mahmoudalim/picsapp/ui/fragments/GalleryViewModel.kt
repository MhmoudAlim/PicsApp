package com.mahmoudalim.picsapp.ui.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mahmoudalim.picsapp.data.repository.UnsplashRepo

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepo
) : ViewModel() {


}