package com.example.vbridge.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.vbridge.R
import com.example.vbridge.ui.base.BaseFragment


class HomeFragment : BaseFragment<HomeViewModel>() {
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun injectDependencies() {
        viewModel = ViewModelProviders.of(
            this,
        )
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun setUpView(view: View) {

    }
}
