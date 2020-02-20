package com.example.vbridge.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.vbridge.VBridgeApplication
import com.example.vbridge.di.component.DaggerFragmentComponent
import com.example.vbridge.di.component.FragmentComponent
import com.example.vbridge.di.module.FragmentModule
import com.example.vbridge.util.display.Toaster
import javax.inject.Inject

abstract class BaseFragment<VM: BaseViewModel>(): Fragment() {

    @Inject lateinit var viewModel: VM

    lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
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

    private fun buildFragmentComponent(): FragmentComponent {
        fragmentComponent = DaggerFragmentComponent
            .builder()
            .applicationComponent((activity?.application as VBridgeApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()
        return fragmentComponent
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

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun provideLayoutId():Int

    protected abstract fun setUpView(view: View)
}