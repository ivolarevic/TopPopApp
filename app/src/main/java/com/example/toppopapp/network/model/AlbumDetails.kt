package com.example.toppopapp.network.model

import com.google.gson.annotations.SerializedName

data class AlbumDetails (
    @SerializedName("nb_tracks") val numberOfTracks : Int,
    @SerializedName("tracks") val tracks: TrackDetails
)