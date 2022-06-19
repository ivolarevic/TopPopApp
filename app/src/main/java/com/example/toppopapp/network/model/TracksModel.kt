package com.example.toppopapp.network.model

import com.example.toppopapp.network.RequestCompleteListener

interface TracksModel {
    fun getTopSongs(callback: RequestCompleteListener<Tracks>)
}