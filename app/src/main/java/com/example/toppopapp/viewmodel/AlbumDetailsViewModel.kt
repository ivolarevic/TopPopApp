package com.example.toppopapp.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.toppopapp.network.ApiModel
import com.example.toppopapp.network.RequestCompleteListener
import com.example.toppopapp.network.model.*

class AlbumDetailsViewModel(application: Application) : AndroidViewModel(application) {
    var albumDetails = MutableLiveData<Tracks>()
    val albumTracks = MutableLiveData<AlbumDetails>()

    private var sharedPreferences = application.getSharedPreferences("MyPref", Context.MODE_PRIVATE)!!

    fun getAlbumDetails(model: ApiModel){
        model.getTopSongs(object : RequestCompleteListener<Tracks> {
            override fun onRequestSuccess(tracks: Tracks) {
                albumDetails.postValue(tracks)
            }
            override fun onRequestFailed(errorMessage: String) {
                Log.d("error", "retrofit failed")
            }
        })
    }

    fun getAlbumTracks(model: ApiModel){
        val idAlbum = sharedPreferences.getLong("albumID", 0)
        model.getAlbumSongs(idAlbum.toString(), object: RequestCompleteListener<AlbumDetails>{
            override fun onRequestSuccess(data: AlbumDetails) {
                albumTracks.postValue(data)
            }
            override fun onRequestFailed(errorMessage: String) {
                Log.d("album", idAlbum.toString())
            }
        })
    }
}