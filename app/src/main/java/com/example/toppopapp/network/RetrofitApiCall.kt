package com.example.toppopapp.network

import android.content.Context
import com.example.toppopapp.network.model.Tracks
import com.example.toppopapp.network.model.TracksModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitApiCall (private val context: Context) : TracksModel {
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
}