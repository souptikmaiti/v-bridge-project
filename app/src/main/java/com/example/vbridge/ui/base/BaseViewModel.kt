package com.example.vbridge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vbridge.R
import com.example.vbridge.util.common.Resource
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected val rxSchedulerProvider: RxSchedulerProvider,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
): ViewModel() {

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    abstract fun onCreate()

    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected open fun forcedLogoutUser() {
        // do something
    }

}