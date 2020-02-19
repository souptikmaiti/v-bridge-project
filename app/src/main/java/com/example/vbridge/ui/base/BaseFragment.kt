package com.example.vbridge.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.vbridge.util.display.Toaster

abstract class BaseFragment<VM: BaseViewModel>(): Fragment() {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setUpObservers()
        viewModel.onCreate()
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(provideLayoutId(), container, false)
        //super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
    }

    protected fun setUpObservers(){
        viewModel.messageString.observe(this, Observer {
            it.data?.run {
                showMessage(this)
            }
        })
        viewModel.messageStringId.observe(this, Observer {
            it.data?.run {
                showMessage(this)
            }
        })
    }

    fun showMessage(msg: String) = context?.let {
        Toaster.show(it, msg)
    }

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    protected abstract fun injectDependencies()

    protected abstract fun provideLayoutId():Int

    protected abstract fun setUpView(view: View)
}