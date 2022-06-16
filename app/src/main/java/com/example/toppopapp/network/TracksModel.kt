package com.example.toppopapp.network

import com.example.toppopapp.network.model.Tracks

interface TracksModel {
    fun getTopSongs(callback: RequestCompleteListener<Tracks>)
}