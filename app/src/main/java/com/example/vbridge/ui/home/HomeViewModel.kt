package com.example.vbridge.ui.home

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.di.ActivityContext
import com.example.vbridge.ui.base.BaseViewModel
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomeViewModel(
    rxSchedulerProvider: RxSchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
): BaseViewModel(rxSchedulerProvider, compositeDisposable, networkHelper) {
    @Inject @ActivityContext lateinit var context:Context
    override fun onCreate() {

    }
}