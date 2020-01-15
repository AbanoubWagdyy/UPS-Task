package com.ups.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseViewModel() : ViewModel() {

    var disposable: Disposable?=null

    @Inject
    lateinit var mContext: Context

    protected var errorObserver = MutableLiveData<String>()

    fun getObserverForError(): MutableLiveData<String> {
        return errorObserver
    }
}