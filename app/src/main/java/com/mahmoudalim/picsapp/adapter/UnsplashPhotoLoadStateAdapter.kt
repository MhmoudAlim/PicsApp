package com.mahmoudalim.picsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudalim.picsapp.databinding.UnsplashLoadStateFooterBinding

class UnsplashPhotoLoadStateAdapter (private val retry: ()-> Unit):
        LoadStateAdapter<UnsplashPhotoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = UnsplashLoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class LoadStateViewHolder(private val binding: UnsplashLoadStateFooterBinding) :
            RecyclerView.ViewHolder(binding.root) {


        init {
            binding.buttonRetryLoad.setOnClickListener {
                 retry.invoke()

            }

        }
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBarLoad.isVisible = loadState is LoadState.Loading
                buttonRetryLoad.isVisible = loadState !is LoadState.Loading
                tvErrorLoad.isVisible = loadState !is LoadState.Loading
            }

        }
    }
}