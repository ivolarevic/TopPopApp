package com.example.toppopapp.data.remote

import com.example.toppopapp.data.entities.Data
import retrofit2.http.GET

interface ArtistService {
    @GET("chart/0/tracks")
    suspend fun getTopSongs(): Data
}