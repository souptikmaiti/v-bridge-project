package com.example.vbridge.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.vbridge.R
import com.example.vbridge.VBridgeApplication
import com.example.vbridge.data.local.pref.UserPreferences
import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.di.component.ActivityComponent
import com.example.vbridge.ui.base.BaseActivity
import com.example.vbridge.util.ViewModelProviderFactory

class SplashActivity() : BaseActivity<SplashViewModel>() {

    override fun provideLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
    }

}
