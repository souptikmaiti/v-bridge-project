package com.example.vbridge.ui.home

import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.ui.base.BaseViewModel
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    rxSchedulerProvider: RxSchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
): BaseViewModel(rxSchedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }
}