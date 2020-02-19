package com.example.vbridge.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.vbridge.data.model.Sample
import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.ui.base.BaseViewModel
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(rxSchedulerProvider: RxSchedulerProvider,
                      compositeDisposable: CompositeDisposable,
                      networkHelper: NetworkHelper,
                      private val userRepository: UserRepository
                      ): BaseViewModel(rxSchedulerProvider, compositeDisposable, networkHelper) {

    val splashData: MutableLiveData<Sample> = MutableLiveData()
    override fun onCreate() {
        splashData.postValue(Sample(id = "demo-id", name = "demo-name"))
    }

}