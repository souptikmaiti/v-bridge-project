package com.example.vbridge.ui.home

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vbridge.R
import com.example.vbridge.di.component.ActivityComponent
import com.example.vbridge.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity() : BaseActivity<HomeViewModel>() {
    private var downloadId: Long = 0

    override fun provideLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
        btn_profile.setOnClickListener {
            beginDownload()
        }
    }

    private fun beginDownload(){
        val dm:DownloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val req: DownloadManager.Request  = DownloadManager.Request(Uri.parse("http://vbridgeworld.com/assets/files/VBRIDGE_PORTFOLIO_2019.pdf"))
            .setTitle("VBridge Portfolio")// Title of the Download Notification
            .setDescription("Downloading Our portfolio")// Description of the Download Notification
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)// Visibility of the download Notification

        downloadId = dm.enqueue(req)
    }

}
