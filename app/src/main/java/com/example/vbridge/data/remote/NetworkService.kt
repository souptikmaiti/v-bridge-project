package com.example.vbridge.data.remote

import com.example.vbridge.data.remote.request.SampleRequest
import com.example.vbridge.data.remote.response.SampleResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {

    @POST(EndPoints.SAMPLE)
    fun postSampleCall(
        @Body request: SampleRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
    ): Single<SampleResponse>

    @GET(EndPoints.SAMPLE)
    fun getSampleCall(
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<SampleResponse>
}