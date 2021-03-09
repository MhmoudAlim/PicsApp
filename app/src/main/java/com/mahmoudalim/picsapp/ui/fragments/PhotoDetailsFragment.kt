package com.mahmoudalim.picsapp.ui.fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mahmoudalim.picsapp.R
import com.mahmoudalim.picsapp.databinding.FragmentPhotoDetailsBinding


class PhotoDetailsFragment : Fragment(R.layout.fragment_photo_details) {

    lateinit var binding: FragmentPhotoDetailsBinding
    private val args by navArgs<PhotoDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoDetailsBinding.bind(view)

        binding.apply {
            val photo = args.photo

            Glide.with(this@PhotoDetailsFragment)
                .load(photo.urls.regular)
                .error(R.drawable.ic_baseline_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        photoOwner.isVisible =true
                        photoCaption.isVisible = photo.description != null
                        return false
                    }
                })
                .into(ivFullPhoto)

            photoCaption.text = photo.description

            val uri = Uri.parse(photo.user.attributionUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            photoOwner.apply {
                text = "Photo by ${photo.user.name} on Usnplash"
                paint.isUnderlineText = true
                setOnClickListener {
                    context.startActivity(intent)
                }
            }
        }

    }


}