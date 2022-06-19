package com.example.toppopapp.network.model

data class Data (
    val id: Int,
    val title: String,
    val title_short: String,
    val link: String,
    val duration: Int,
    val md5_image: String,
    val position: Int,
    val artist: Artist,
    val album: Album,
    val type: String,
)
