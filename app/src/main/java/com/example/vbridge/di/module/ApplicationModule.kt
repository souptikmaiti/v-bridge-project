package com.example.vbridge.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.vbridge.BuildConfig
import com.example.vbridge.VBridgeApplication
import com.example.vbridge.data.local.db.DatabaseService
import com.example.vbridge.data.remote.NetworkService
import com.example.vbridge.data.remote.Networking
import com.example.vbridge.di.ApplicationContext
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule (private val application: VBridgeApplication){

    @Singleton
    @Provides
    fun providesApplication(): Application = application

    @Singleton
    @Provides
    @ApplicationContext
    fun providesContext(): Context = application

    @Singleton
    @Provides
    fun providesNetworkService(): NetworkService = Networking.create(
        apiKey = BuildConfig.API_KEY,
        baseUrl = BuildConfig.BASE_URL,
        cacheDir = application.cacheDir,
        cacheSize = 10 * 1024 *1024  // 10 MB
    )

    @Singleton
    @Provides
    fun providesDatabaseService(): DatabaseService = Room.databaseBuilder(
        application,
        DatabaseService::class.java,
        "v_bridge_project_db"
    ).build()

    @Singleton
    @Provides
    fun providesSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("v_bridge_project_pref", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesNetworkHelper(): NetworkHelper = NetworkHelper(context = application)

    @Provides
    fun providesRxSchedulerProvider(): RxSchedulerProvider = RxSchedulerProvider()

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}