package com.example.toppopapp.network

import com.example.toppopapp.network.model.AlbumDetails
import com.example.toppopapp.network.model.Tracks

interface ApiModel {
    fun getTopSongs(callback: RequestCompleteListener<Tracks>)
    fun getAlbumSongs(albumId: String, callback: RequestCompleteListener<AlbumDetails>)
}