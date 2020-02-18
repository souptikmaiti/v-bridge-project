package com.example.vbridge.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SampleRequest(
    @Expose
    @SerializedName("id")
    var id: String
) {
}