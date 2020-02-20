package com.example.vbridge

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.vbridge.data.local.db.DatabaseService
import com.example.vbridge.data.remote.NetworkService
import com.example.vbridge.data.remote.Networking
import com.example.vbridge.di.component.ApplicationComponent
import com.example.vbridge.di.component.DaggerApplicationComponent
import com.example.vbridge.di.module.ApplicationModule
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class VBridgeApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}