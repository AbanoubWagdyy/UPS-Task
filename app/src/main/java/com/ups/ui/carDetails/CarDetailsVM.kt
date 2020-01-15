package com.ups.ui.carDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ups.BuildConfig
import com.ups.R
import com.ups.data.RepositorySource
import com.ups.data.model.CarDetailsResponse
import javax.inject.Inject
import com.ups.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CarDetailsVM @Inject constructor(var mRepositorySource: RepositorySource) : BaseViewModel() {

    private var carDetailsLiveData = MutableLiveData<CarDetailsResponse>()
    private var exterior_url =""

    @SuppressLint("CheckResult")
    fun getCarDetails() {
        disposable = mRepositorySource.getCarDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ carDetails ->
                Log.d("", carDetails.toString())
                validateCarDetailsResponse(carDetails)
            }, {
                errorObserver.value = it.message
            })
    }

    private fun validateCarDetailsResponse(carDetailsResponse: CarDetailsResponse?) {
        if (carDetailsResponse != null) {
            if (carDetailsResponse.status.equals(BuildConfig.SUCCESS)) {
                carDetailsLiveData.value = carDetailsResponse
                exterior_url = carDetailsResponse.data[0].models_exterior_image
            } else {
                errorObserver.value = carDetailsResponse.message
            }
        } else {
            errorObserver.value = mContext.getString(R.string.data_not_found)
        }
    }

    fun getCarDetailsLiveData(): MutableLiveData<CarDetailsResponse> {
        return carDetailsLiveData
    }

    fun getExteriorImageURL(): String {
        return exterior_url
    }
}