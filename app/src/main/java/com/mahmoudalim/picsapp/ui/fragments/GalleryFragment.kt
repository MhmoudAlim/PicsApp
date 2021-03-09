package com.mahmoudalim.picsapp.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mahmoudalim.picsapp.R
import com.mahmoudalim.picsapp.adapter.UnsplashPhotoAdapter
import com.mahmoudalim.picsapp.adapter.UnsplashPhotoLoadStateAdapter
import com.mahmoudalim.picsapp.data.models.UnsplashPhoto
import com.mahmoudalim.picsapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery),
        UnsplashPhotoAdapter.OnItemClickListener {

    private lateinit var binding: FragmentGalleryBinding

    private val viewModel by viewModels<GalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)
        val photoAdapter = UnsplashPhotoAdapter(this)
        setHasOptionsMenu(true)

        setupRecycler(photoAdapter)

        viewModel.photos.observe(viewLifecycleOwner) {
            photoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setupLoadStateOrSearch(photoAdapter)
    }

    override fun OnItemClick(photo: UnsplashPhoto) {
        val action =
            GalleryFragmentDirections.actionGalleryFragment2ToPhotoDetailsFragment(photo)

        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchIcon = menu.findItem(R.id.search_icon)
        val searchView = searchIcon.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    searchView.clearFocus()
                    viewModel.searchPhotos(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

    }

    private fun setupLoadStateOrSearch(adapter: UnsplashPhotoAdapter) {
        adapter.addLoadStateListener {
            binding.apply {
                progressBar.isVisible = it.source.refresh is LoadState.Loading
                recyclerView.isVisible = it.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = it.source.refresh is LoadState.Error
                tvError.isVisible = it.source.refresh is LoadState.Error

                //empty view or no search results found
                if (it.source.refresh is LoadState.NotLoading &&
                    it.append.endOfPaginationReached &&
                    adapter.itemCount == 0
                ) {
                    recyclerView.isVisible = false
                    tvNoresults.isVisible = true
                } else {
                    tvNoresults.isVisible = false
                }
            }
        }
    }

    private fun setupRecycler(adapter: UnsplashPhotoAdapter) {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter { adapter.retry() },
                footer = UnsplashPhotoLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener {
                adapter.retry()
            }
        }
    }
}