package com.ups.ui.carList

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ups.data.RepositorySource
import javax.inject.Inject
import com.ups.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.ups.BuildConfig.SUCCESS
import com.ups.R
import com.ups.data.model.Car
import com.ups.data.model.CarListResponse

class CarListVM @Inject constructor(var mRepositorySource: RepositorySource) : BaseViewModel() {

    private var carListLiveData = MutableLiveData<List<Car>>()

    @SuppressLint("CheckResult")
    fun getCarList() {
        disposable = mRepositorySource.getCarList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ carListResponse ->
                Log.d("", carListResponse.toString())
                validateCarListResponse(carListResponse)
            }, {
                errorObserver.value = it.message
            })
    }

    private fun validateCarListResponse(carListResponse: CarListResponse?) {
        if (carListResponse != null) {
            if (carListResponse.status.equals(SUCCESS)) {
                carListLiveData.value = carListResponse.data
            } else {
                errorObserver.value = carListResponse.message
            }
        } else {
            errorObserver.value = mContext.getString(R.string.data_not_found)
        }
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getCarListLiveData(): MutableLiveData<List<Car>> {
        return carListLiveData
    }

    fun saveCar(car: Car) {
        mRepositorySource.saveCar(car)
    }
}