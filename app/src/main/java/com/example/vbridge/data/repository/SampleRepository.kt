package com.example.vbridge.data.repository

import com.example.vbridge.data.local.db.DatabaseService
import com.example.vbridge.data.local.pref.UserPreferences
import com.example.vbridge.data.model.Sample
import com.example.vbridge.data.remote.NetworkService
import com.example.vbridge.data.remote.request.SampleRequest
import io.reactivex.Single

class SampleRepository(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun getSample(id: String): Single<List<Sample>> =
        networkService.postSampleCall(SampleRequest(id)).map {
            it.data
        }
}