package com.example.toppopapp.network.model

import com.example.toppopapp.network.RequestCompleteListener

interface AlbumModel {
    fun getAlbumSongs(albumId: String, callback: RequestCompleteListener<AlbumDetails>)
}