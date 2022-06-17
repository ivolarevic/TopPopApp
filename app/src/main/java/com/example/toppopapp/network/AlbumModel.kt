package com.example.toppopapp.network

import com.example.toppopapp.network.model.AlbumDetails

interface AlbumModel {
    fun getAlbumSongs(albumId: String, callback: RequestCompleteListener<AlbumDetails>)
}