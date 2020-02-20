package com.example.vbridge.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vbridge.R
import com.example.vbridge.di.component.ActivityComponent
import com.example.vbridge.ui.base.BaseActivity

class HomeActivity() : BaseActivity<HomeViewModel>() {

    override fun provideLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
    }

}
