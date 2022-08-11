package com.example.toppopapp.data.entities

data class SongDetails(
    val id: Int,
    val title: String,
    val position : Int,
    val duration : Int,
    val artist: ArtistDetails,
    val album: AlbumDetails
)
