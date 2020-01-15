package com.ups.data.network

import com.ups.data.model.CarDetailsResponse
import com.ups.data.model.CarListResponse
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServiceAPI {

    @Headers("Content-Type: application/json")
    @GET("dev/api/api.php?function=allmodels")
    fun getCarList(): Observable<CarListResponse>

    @Headers("Content-Type: application/json")
    @GET("api/api.php?function=allmodelsbybrand")
    fun getCarDetails(@Query("carId") carId: String): Observable<CarDetailsResponse>
}