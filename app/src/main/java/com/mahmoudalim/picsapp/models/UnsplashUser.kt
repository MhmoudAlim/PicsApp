package com.mahmoudalim.picsapp.models

import android.os.Parcelable
import com.mahmoudalim.picsapp.util.constants.Companion.APP_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashUser(
    val name: String,
    val username: String,
) : Parcelable {

    val attributionUrl
        get() =
            "https://unsplash.com/$username?utm_source=$APP_NAME&utm_medium=referral"

}