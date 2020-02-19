package com.example.vbridge

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.vbridge.data.local.db.DatabaseService
import com.example.vbridge.data.remote.NetworkService
import com.example.vbridge.data.remote.Networking
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

object VBridgeApplication: Application() {
    lateinit var networkService: NetworkService
    lateinit var databaseService: DatabaseService
    lateinit var sharedPreference: SharedPreferences
    lateinit var networkHelper: NetworkHelper
    lateinit var rxScheduleProvider: RxSchedulerProvider
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        if (!this::networkService.isInitialized){
            networkService = Networking.create(
                apiKey = "api-key",
                baseUrl = "base-url",
                cacheDir = this.cacheDir,
                cacheSize = 10 * 1024 *1024  // 10 MB
            )
        }

        if (!this::databaseService.isInitialized) {
            databaseService = Room.databaseBuilder(
                applicationContext,
                DatabaseService::class.java,
                "v_bridge_project_db"
            ).build()
        }

        if(!this::sharedPreference.isInitialized){
            sharedPreference = getSharedPreferences("v_bridge_project_pref", Context.MODE_PRIVATE)
        }

        if (!this::networkHelper.isInitialized) {
            networkHelper = NetworkHelper(context = applicationContext)
        }

        if(!this::rxScheduleProvider.isInitialized){
            rxScheduleProvider = RxSchedulerProvider()
        }

        if (!this::compositeDisposable.isInitialized) {
            compositeDisposable = CompositeDisposable()
        }
    }
}