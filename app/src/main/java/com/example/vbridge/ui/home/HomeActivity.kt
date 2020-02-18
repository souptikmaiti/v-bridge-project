package com.example.vbridge.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vbridge.R
import com.example.vbridge.ui.base.BaseActivity

class HomeActivity() : BaseActivity<HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
