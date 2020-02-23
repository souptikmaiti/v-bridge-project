package com.example.vbridge.ui.home

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.vbridge.R
import com.example.vbridge.di.component.ActivityComponent
import com.example.vbridge.ui.base.BaseActivity
import com.example.vbridge.util.display.Toaster
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity() : BaseActivity<HomeViewModel>() {
    companion object{
        val TAG = this::class.java.simpleName
    }
    private var downloadId: Long = 0
    val EXTERNAL_STORAGE_PERMISSION = 100;
    override fun provideLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
        btn_profile.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    beginDownload()
                }else{
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), EXTERNAL_STORAGE_PERMISSION)
                }
            }else{
                beginDownload()
            }
        }

        ib_whatsapp.setOnClickListener{
            openWhatsapp()
        }

        ib_fb.setOnClickListener {
            openFacebook()
        }
    }

    private fun beginDownload(){
        val dm:DownloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val request: DownloadManager.Request  = DownloadManager.Request(Uri.parse(
            //"http://vbridgeworld.com/assets/files/VBRIDGE_PORTFOLIO_2019.pdf"
            "https://file-examples.com/wp-content/uploads/2017/10/file_example_JPG_100kB.jpg"
             ))
            .setTitle("VBridge Portfolio")// Title of the Download Notification
            .setDescription("Downloading Our portfolio")// Description of the Download Notification
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE) // network types
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)// Visibility of the download Notification
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS , "${System.currentTimeMillis()}")
            request.allowScanningByMediaScanner()

            downloadId = dm.enqueue(request)
    }

    private fun openWhatsapp(){
        val contact = "+91 8443990121" // use country code with your phone number

        val url = "https://api.whatsapp.com/send?phone=$contact"
        try {
            val pm: PackageManager = packageManager
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val whatappIntent = Intent(Intent.ACTION_VIEW)
            whatappIntent.data = Uri.parse(url)
            startActivity(whatappIntent)
        } catch (e: PackageManager.NameNotFoundException) {
            Toaster.show(this, "Whatsapp is not installed in your device")
            Log.d(TAG, e.toString())
        }
    }

    private fun openFacebook(){
        val fbUrl = "https://www.facebook.com/abridge2k17/"
        val fbId = "fb://page/303326343423911"
        var fbIntent : Intent
        try {
            val pm: PackageManager = packageManager
            pm.getPackageInfo("com.facebook.katana", 0)
            fbIntent = Intent(Intent.ACTION_VIEW)
            fbIntent.data = Uri.parse(fbId)
        } catch (e: Exception) {
            fbIntent = Intent(Intent.ACTION_VIEW)
            fbIntent.data = Uri.parse(fbUrl)

        }
        startActivity(fbIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            EXTERNAL_STORAGE_PERMISSION -> {
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    beginDownload()
                }else{
                    Toaster.show(this, "Permission Denied.")
                }
            }
        }
    }


}
