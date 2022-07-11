package com.example.toppopapp.network.model

data class TrackInformation(
    val position:Int,
    val songName: String,
    val artistName: String,
    val duration: Int,
    val albumId: Long,
    val artistId: Long,
)