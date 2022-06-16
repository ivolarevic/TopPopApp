package com.example.toppopapp.network.model

data class Data (
    val id: Int,
    val title: String,
    val title_short: String,
    val title_version: String,
    val link: String,
    val duration: Int,
    val rank: Long,
    val explicit_lyrics: Boolean,
    val explicit_content_lyrics: Int,
    val explicit_content_cover : Int,
    val preview: String,
    val md5_image: String,
    val position: Int,
    val artist: Artist,
    val album: Album,
    val type: String,
)
