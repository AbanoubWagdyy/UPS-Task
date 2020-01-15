package com.ups.data

import com.ups.data.model.Car
import com.ups.data.model.CarDetailsResponse
import com.ups.data.model.CarListResponse
import io.reactivex.Observable

interface RepositorySource {
    fun getCarList(): Observable<CarListResponse>
    fun getCarDetails(): Observable<CarDetailsResponse>
    fun saveCar(car: Car)
}