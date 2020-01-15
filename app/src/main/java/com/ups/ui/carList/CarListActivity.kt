package com.ups.ui.carList

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ups.R
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ups.data.model.Car
import com.ups.di.qualifiers.ViewModelInjection
import com.ups.di.ViewModelInjectionField
import com.ups.ui.BaseActivity
import com.ups.ui.carDetails.CarDetailsActivity
import com.ups.ui.carList.adapter.CarListAdapter
import kotlinx.android.synthetic.main.activity_car_list.*

class CarListActivity : BaseActivity(), CarListAdapter.OnCarClickListener {

    private var viewModel: CarListVM? = null

    @Inject
    @ViewModelInjection
    lateinit var viewModelInjection: ViewModelInjectionField<CarListVM>

    override fun layoutRes() = R.layout.activity_car_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        viewModel!!.getCarListLiveData().observe(this, Observer { cars ->
            progressBar.visibility = View.GONE
            recyclerCars.layoutManager = LinearLayoutManager(applicationContext)
            recyclerCars.adapter = CarListAdapter(
                applicationContext,
                cars as MutableList<Car>,
                this
            )
        })

        viewModel!!.getCarList()
    }

    override fun onCarPressed(car: Car) {
        viewModel!!.saveCar(car)
        val intent = Intent(this, CarDetailsActivity::class.java)
        startActivity(intent)
    }
}