package com.mahmoudalim.picsapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id : String,
    val description : String?,
    val created_at : String?,
    val urls : UnsplashPhotoUrls,
    val user : UnsplashUser,
): Parcelable
