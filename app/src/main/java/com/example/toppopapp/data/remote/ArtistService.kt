package com.example.toppopapp.data.remote

import com.example.toppopapp.data.entities.ArtistList
import com.example.toppopapp.data.entities.SongList
import retrofit2.Response
import retrofit2.http.GET

interface ArtistService {
    @GET("chart/0/tracks")
    suspend fun getTopSongs(): Response<SongList>

    @GET("chart/0/artists")
    suspend fun getArtistDetails(): Response<ArtistList>

}