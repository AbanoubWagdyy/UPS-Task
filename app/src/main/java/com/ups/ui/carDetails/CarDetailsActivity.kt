package com.ups.ui.carDetails

import android.content.Intent
import com.ups.R
import javax.inject.Inject
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ups.data.setImageUrl
import com.ups.di.qualifiers.ViewModelInjection
import com.ups.di.ViewModelInjectionField
import com.ups.ui.BaseActivity
import com.ups.ui.ExteriorActivity
import kotlinx.android.synthetic.main.activity_car_details.*
import kotlinx.android.synthetic.main.activity_car_list.*
import kotlinx.android.synthetic.main.app_bar_homepage.*
import org.json.JSONObject

class CarDetailsActivity : BaseActivity() {

    private var viewModel: CarDetailsVM? = null

    @Inject
    @ViewModelInjection
    lateinit var viewModelInjection: ViewModelInjectionField<CarDetailsVM>

    override fun layoutRes() = R.layout.activity_car_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        viewModel = viewModelInjection.get()
        initObservers()
    }

    private fun initObservers() {
        viewModel!!.getObserverForError().observe(this, Observer {
            progressBar.visibility = View.GONE
            it?.let { it1 ->
                Snackbar.make(
                    findViewById(android.R.id.content),
                    it1,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        viewModel!!.getCarDetailsLiveData().observe(this, Observer {
            tvTitle.text = it.data[0].models_name_en
            description.text = it.data[0].models_spec_en
            modelImage.setImageUrl(applicationContext, it.data[0].models_image)

            initListeners()
        })

        viewModel!!.getCarDetails()
    }

    private fun initListeners() {
        showInterior.setOnClickListener {
        }
        showExterior.setOnClickListener {
            val intent = Intent(applicationContext, ExteriorActivity::class.java)
            intent.putExtra("url", viewModel!!.getExteriorImageURL())
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}