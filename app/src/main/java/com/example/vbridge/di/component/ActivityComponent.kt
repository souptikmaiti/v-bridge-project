package com.example.vbridge.di.component

import com.example.vbridge.di.ActivityScope
import com.example.vbridge.di.module.ActivityModule
import com.example.vbridge.ui.home.HomeActivity
import com.example.vbridge.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: HomeActivity)
}