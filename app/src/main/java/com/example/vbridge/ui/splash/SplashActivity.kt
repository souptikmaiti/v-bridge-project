package com.example.vbridge.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.vbridge.R
import com.example.vbridge.VBridgeApplication
import com.example.vbridge.data.local.pref.UserPreferences
import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.ui.base.BaseActivity
import com.example.vbridge.util.ViewModelProviderFactory

class SplashActivity() : BaseActivity<SplashViewModel>() {

    override fun provideLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun injectDependencies() {
        var userRepository: UserRepository = UserRepository(
            networkService = VBridgeApplication.networkService,
            databaseService = VBridgeApplication.databaseService,
            userPreferences = UserPreferences(VBridgeApplication.sharedPreference)
        )
        viewModel = ViewModelProviders.of(
            this,
            ViewModelProviderFactory(
                SplashViewModel::class,
                {
                    SplashViewModel(rxSchedulerProvider = VBridgeApplication.rxScheduleProvider,
                        compositeDisposable = VBridgeApplication.compositeDisposable,
                        networkHelper = VBridgeApplication.networkHelper,
                        userRepository = userRepository)
                }
            )
        ).get(SplashViewModel::class.java)
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
    }

}
