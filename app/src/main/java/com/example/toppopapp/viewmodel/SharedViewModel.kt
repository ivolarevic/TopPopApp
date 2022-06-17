package com.example.toppopapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toppopapp.network.AlbumModel
import com.example.toppopapp.network.data.AlbumInformation
import com.example.toppopapp.network.RequestCompleteListener
import com.example.toppopapp.network.TracksModel
import com.example.toppopapp.network.model.AlbumDetails
import com.example.toppopapp.network.model.Tracks

class SharedViewModel : ViewModel() {

    val idArtist = MutableLiveData<Int>()
    val idAlbum = MutableLiveData<Long>()

    var albumDetails = MutableLiveData<AlbumInformation>()
    val albumTracks = MutableLiveData<AlbumDetails>()

    fun setIdArtist(id : Int){
        idArtist.value = id
    }

    fun setIdAlbum(id: Long){
        idAlbum.value = id
    }

    fun getAlbumDetails(model: TracksModel){
        model.getTopSongs(object : RequestCompleteListener<Tracks> {
            override fun onRequestSuccess(tracks: Tracks) {
                val details = AlbumInformation(
                    artistName = tracks.data[idArtist.value!!].artist.name,
                    songName = tracks.data[idArtist.value!!].title,
                    albumName = tracks.data[idArtist.value!!].album.title,
                    cover = tracks.data[idArtist.value!!].album.cover_medium
                )
                albumDetails.postValue(details)
            }

            override fun onRequestFailed(errorMessage: String) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getAlbumTracks(model: AlbumModel){
        model.getAlbumSongs(idAlbum.value.toString(), object: RequestCompleteListener<AlbumDetails>{
            override fun onRequestSuccess(data: AlbumDetails) {
                albumTracks.postValue(data)
            }
            override fun onRequestFailed(errorMessage: String) {
                Log.d("album", idAlbum.value.toString())
            }
        })
    }
}