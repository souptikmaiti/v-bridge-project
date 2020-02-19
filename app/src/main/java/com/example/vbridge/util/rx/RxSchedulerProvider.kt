package com.example.vbridge.util.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxSchedulerProvider {
    fun computation(): Scheduler = Schedulers.computation()

    fun io(): Scheduler = Schedulers.io()

    fun ui(): Scheduler = AndroidSchedulers.mainThread()
}