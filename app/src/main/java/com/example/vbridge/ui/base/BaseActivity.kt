package com.example.vbridge.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.vbridge.VBridgeApplication
import com.example.vbridge.di.component.ActivityComponent
import com.example.vbridge.di.component.ApplicationComponent
import com.example.vbridge.di.component.DaggerActivityComponent
import com.example.vbridge.di.module.ActivityModule
import com.example.vbridge.ui.splash.SplashViewModel
import com.example.vbridge.util.display.Toaster
import javax.inject.Inject

abstract class BaseActivity<VM: BaseViewModel>() : AppCompatActivity() {

    @Inject lateinit var viewModel: VM

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setUpObservers()
        setUpViews(savedInstanceState)
        viewModel.onCreate()
    }

    private fun buildActivityComponent(): ActivityComponent{
        activityComponent = DaggerActivityComponent.builder()
            .applicationComponent((application as VBridgeApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
        return activityComponent
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

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