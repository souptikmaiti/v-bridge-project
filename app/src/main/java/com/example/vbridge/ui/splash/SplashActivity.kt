package com.example.vbridge.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vbridge.R
import com.example.vbridge.ui.base.BaseActivity

class SplashActivity() : BaseActivity<SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
