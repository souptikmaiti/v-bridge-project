package com.example.vbridge.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.vbridge.ui.splash.SplashViewModel
import com.example.vbridge.util.display.Toaster

abstract class BaseActivity<VM: BaseViewModel>() : AppCompatActivity() {

    lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setUpObservers()
        setUpViews(savedInstanceState)
        viewModel.onCreate()
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies()

    protected abstract fun setUpViews(savedInstanceState: Bundle?)

    protected fun setUpObservers() {
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

    fun showMessage(s: String) {
        Toaster.show(applicationContext, s)
    }

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStackImmediate()
        }else {
            super.onBackPressed()
        }
    }
}