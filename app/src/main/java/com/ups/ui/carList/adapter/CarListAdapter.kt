package com.ups.ui.carList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ups.BuildConfig
import com.ups.R
import com.ups.data.model.Car
import com.ups.data.setImageUrl
import kotlinx.android.synthetic.main.car_list_item.view.*

class CarListAdapter(
    val mContext: Context,
    private var cars: MutableList<Car>,
    val onCarClickListener: OnCarClickListener
) :
    RecyclerView.Adapter<CarListAdapter.CarListViewHolder>() {

    private val inflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        return CarListViewHolder(
            inflater.inflate(
                R.layout.car_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        holder.bindCarData(cars[position])
    }

    inner class CarListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCarData(car: Car) {
            itemView.carImage.setImageUrl(mContext, car.models_image)
            itemView.carName.text = car.models_name_en
            itemView.setOnClickListener {
                onCarClickListener.onCarPressed(car)
            }
        }
    }

    interface OnCarClickListener {
        fun onCarPressed(car: Car)
    }
}