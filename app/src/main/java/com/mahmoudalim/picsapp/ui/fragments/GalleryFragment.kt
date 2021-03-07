package com.mahmoudalim.picsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mahmoudalim.picsapp.R
import com.mahmoudalim.picsapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private lateinit var binding: FragmentGalleryBinding

    private val viewModel by viewModels<GalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)

        viewModel.photos.observe(viewLifecycleOwner) {
        }
    }
}