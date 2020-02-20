package com.example.vbridge.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.di.ActivityContext
import com.example.vbridge.ui.base.BaseActivity
import com.example.vbridge.ui.home.HomeViewModel
import com.example.vbridge.ui.splash.SplashViewModel
import com.example.vbridge.util.ViewModelProviderFactory
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @ActivityContext
    @Provides
    fun providesContext(): Context = activity

    @Provides
    fun providesLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun providesSplashViewModel(rxSchedulerProvider: RxSchedulerProvider,
                                compositeDisposable: CompositeDisposable,
                                networkHelper: NetworkHelper,
                                userRepository: UserRepository
    ): SplashViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(
            SplashViewModel::class,
            {
               SplashViewModel(
                   rxSchedulerProvider = rxSchedulerProvider,
                   compositeDisposable = compositeDisposable,
                   networkHelper = networkHelper,
                   userRepository = userRepository
               )
            }
        )).get(SplashViewModel::class.java)

    @Provides
    fun providesHomeViewModel(
        rxSchedulerProvider: RxSchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): HomeViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(
            HomeViewModel::class,
            {
                HomeViewModel(
                    rxSchedulerProvider = rxSchedulerProvider,
                    compositeDisposable = compositeDisposable,
                    networkHelper = networkHelper,
                    userRepository = userRepository
                )
            }
        )).get(HomeViewModel::class.java)

}