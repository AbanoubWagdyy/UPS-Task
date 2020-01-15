package com.ups.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ups.R
import kotlinx.android.synthetic.main.activity_exterior.*

class ExteriorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exterior)

        webview.loadUrl(intent.extras!!.getString("url"), false)
    }
}