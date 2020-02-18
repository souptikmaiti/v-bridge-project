package com.example.vbridge.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {

    const val HEADER_API_KEY = "x-api-key"
    const val HEADER_ACCESS_TOKEN = "x-access-token"
    const val HEADER_USER_ID = "x-user-id"

    private const val NETWORK_CALL_TIMEOUT: Long = 60
    internal lateinit var API_KEY: String

    fun create(apiKey: String, baseUrl: String, cacheDir: File, cacheSize: Long): NetworkService{
        API_KEY = apiKey

        val client = OkHttpClient.Builder()
            .cache(Cache( cacheDir, cacheSize))
            .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor( HttpLoggingInterceptor()
                .apply {
                    level =  HttpLoggingInterceptor.Level.BODY
                })
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(NetworkService::class.java)

    }
}