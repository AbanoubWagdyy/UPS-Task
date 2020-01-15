package com.ups.data

import com.ups.data.model.Car
import com.ups.data.model.CarDetailsResponse
import com.ups.data.model.CarListResponse
import com.ups.data.network.ServiceAPI
import io.reactivex.Observable
import javax.inject.Inject

class DataRepository @Inject constructor(var mServiceAPI: ServiceAPI) : RepositorySource {

    private var car: Car? = null

    override fun getCarList(): Observable<CarListResponse> {
        return mServiceAPI.getCarList()
    }

    override fun getCarDetails(): Observable<CarDetailsResponse> {
        return mServiceAPI.getCarDetails(car!!.models_id)
    }

    override fun saveCar(car: Car) {
        this.car = car
    }
}