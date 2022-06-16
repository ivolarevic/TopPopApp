package com.example.toppopapp.network.model

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("data") val data: List<Data>,
    @SerializedName("total") val total: Int,
)
