package com.example.vbridge.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sample(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("id")
    val id: String?
) {
}