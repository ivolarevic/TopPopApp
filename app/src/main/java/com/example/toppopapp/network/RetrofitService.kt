package com.example.toppopapp.network

import com.example.toppopapp.network.model.Tracks
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("0/tracks")
    fun getTopSongs():Call<Tracks>

    companion object{
        var BASE_URL="https://api.deezer.com/chart/"

        fun create() : RetrofitService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }

}