package com.example.toppopapp.network

import com.example.toppopapp.network.model.AlbumDetails
import com.example.toppopapp.network.model.Tracks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitApiCall () : ApiModel {

    override fun getTopSongs(callback: RequestCompleteListener<Tracks>) {
        val interfaceAPI : RetrofitService = RetrofitService.create()
        val call : Call<Tracks> = interfaceAPI.getTopSongs()

        call.enqueue(object : Callback<Tracks> {
            override fun onResponse(call: Call<Tracks>, response: Response<Tracks>) {
                if (response.isSuccessful) {
                    callback.onRequestSuccess(response.body()!!)
                }else{
                    callback.onRequestFailed(response.message())
                }
            }
            override fun onFailure(call: Call<Tracks>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }

     override fun getAlbumSongs(albumId: String, callback: RequestCompleteListener<AlbumDetails>) {
        val interfaceAPI : RetrofitService = RetrofitService.create()
        val call : Call<AlbumDetails> = interfaceAPI.getAlbumSongs(albumId)

        call.enqueue(object : Callback<AlbumDetails> {
            override fun onResponse(call: Call<AlbumDetails>, response: Response<AlbumDetails>) {
                if (response.isSuccessful) {
                    callback.onRequestSuccess(response.body()!!)
                }else{
                    callback.onRequestFailed(response.message())
                }
            }
            override fun onFailure(call: Call<AlbumDetails>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }
}