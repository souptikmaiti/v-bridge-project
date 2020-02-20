package com.example.vbridge.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vbridge.data.repository.UserRepository
import com.example.vbridge.ui.home.HomeViewModel
import com.example.vbridge.util.ViewModelProviderFactory
import com.example.vbridge.util.network.NetworkHelper
import com.example.vbridge.util.rx.RxSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun providesLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun providesHomeViewModel(
        rxSchedulerProvider: RxSchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): HomeViewModel =
        ViewModelProviders.of(fragment, ViewModelProviderFactory(
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