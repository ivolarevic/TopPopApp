package com.example.toppopapp.network

import com.example.toppopapp.network.model.AlbumDetails
import com.example.toppopapp.network.model.Tracks
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("chart/0/tracks")
    fun getTopSongs():Call<Tracks>

    @GET("album/{album_id}")
    fun getAlbumSongs(@Path("album_id") album_id:String ):Call<AlbumDetails>

    companion object{
        var BASE_URL="https://api.deezer.com"

        fun create() : RetrofitService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }

}