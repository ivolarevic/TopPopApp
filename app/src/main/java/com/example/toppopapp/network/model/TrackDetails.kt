package com.example.toppopapp.network.model

import com.google.gson.annotations.SerializedName

data class TrackDetails (
    @SerializedName("data") val data: List<AlbumData>
)