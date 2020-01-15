package com.ups.data

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ups.BuildConfig

fun ImageView.setImageUrl(context: Context, path: String) {
    val image = BuildConfig.BASE_URL + "api/" + path
    Glide.with(context)
        .asBitmap()
        .load(image)
        .into(this)
}