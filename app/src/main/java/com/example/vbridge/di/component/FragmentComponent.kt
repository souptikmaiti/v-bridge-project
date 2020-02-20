package com.example.vbridge.di.component

import com.example.vbridge.di.FragmentScope
import com.example.vbridge.di.module.FragmentModule
import com.example.vbridge.ui.home.HomeFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
}