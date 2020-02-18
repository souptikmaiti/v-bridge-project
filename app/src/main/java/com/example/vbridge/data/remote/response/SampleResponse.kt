package com.example.vbridge.data.remote.response

import com.example.vbridge.data.model.Sample
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SampleResponse(
    @Expose
    @SerializedName("statusCode")
    var statusCode: String,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("data")
    val data: List<Sample>
) {
}