package com.example.vbridge.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.vbridge.VBridgeApplication
import com.example.vbridge.data.local.db.DatabaseService
import com.example.vbridge.data.remote.NetworkService
import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.di.ApplicationContext
import com.example.vbridge.di.module.ApplicationModule
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: VBridgeApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreference(): SharedPreferences

    fun getUserRepository(): UserRepository

    fun getNetworkHelper(): NetworkHelper

    fun getRxSchedulerProvider(): RxSchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable
}