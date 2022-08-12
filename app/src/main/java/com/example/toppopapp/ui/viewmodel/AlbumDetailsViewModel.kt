package com.example.toppopapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel



class AlbumDetailsViewModel(application: Application) : AndroidViewModel(application) {
    /*private var model : ApiModel = RetrofitApiCall()

    private var _albumDetails = MutableLiveData<Tracks>()
    val albumDetails : MutableLiveData<Tracks>
        get() = _albumDetails

    private var _albumTracks = MutableLiveData<AlbumDetails>()
    val albumTracks : MutableLiveData<AlbumDetails>
        get() = _albumTracks


    private var sharedPreferences = application.getSharedPreferences("MyPref", Context.MODE_PRIVATE)!!

     fun getAlbumDetails(){
        model.getTopSongs(object : RequestCompleteListener<Tracks> {
            override fun onRequestSuccess(tracks: Tracks) {
                Log.d("albumDetails", tracks.toString())
                _albumDetails.postValue(tracks)
            }
            override fun onRequestFailed(errorMessage: String) {
                Log.d("error", "retrofit failed")
            }
        })

    }

     fun getAlbumTracks(){
        val idAlbum = sharedPreferences.getLong("albumID", 0)
        Log.d("albumID", idAlbum.toString())

        model.getAlbumSongs(idAlbum.toString(), object: RequestCompleteListener<AlbumDetails>{
            override fun onRequestSuccess(data: AlbumDetails) {
                _albumTracks.postValue(data)
            }
            override fun onRequestFailed(errorMessage: String) {
                Log.d("error", "retrofit failed")
            }
        })
    }*/
}