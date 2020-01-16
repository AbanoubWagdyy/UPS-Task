package com.ups.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.panoramagl.utils.PLUtils
import com.ups.R
import com.ups.data.convertToByteArray
import kotlinx.android.synthetic.main.activity_interior.*


class InteriorActivity : AppCompatActivity() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interior)

        val DEMO_PANORAMA_LINK = intent.extras?.getString("url")

        Glide.with(this).asBitmap().load(DEMO_PANORAMA_LINK).into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                vrPanoramaView.loadImageFromBitmap(resource, null)
            }
        })
    }
}