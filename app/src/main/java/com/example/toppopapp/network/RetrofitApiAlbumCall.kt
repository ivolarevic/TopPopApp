package com.example.toppopapp.network

import android.content.Context
import android.util.Log
import com.example.toppopapp.network.model.AlbumDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitApiAlbumCall (private val context: Context) : AlbumModel {
    override fun getAlbumSongs(albumId: String, callback: RequestCompleteListener<AlbumDetails>) {

        Log.d("api_call", albumId.toString())
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
                Log.d("msg", t.toString())
                Log.d("id2", albumId.toString())
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }
}