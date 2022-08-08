package com.example.toppopapp.data.remote

import com.example.toppopapp.data.entities.Artist
//import com.example.toppopapp.network.model.AlbumDetails
import retrofit2.Response
import retrofit2.http.GET

interface ArtistService {
    @GET("chart/0/tracks")
    suspend fun getTopSongs(): Response<Artist>

    /*@GET("album/{album_id}")
    suspend fun getAlbumSongs(@Path("album_id") album_id:String ): Call<AlbumDetails>
    */
}