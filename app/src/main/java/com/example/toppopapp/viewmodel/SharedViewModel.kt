package com.example.toppopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toppopapp.network.data.AlbumInformation
import com.example.toppopapp.network.RequestCompleteListener
import com.example.toppopapp.network.TracksModel
import com.example.toppopapp.network.model.Tracks

class SharedViewModel : ViewModel() {

    val idArtist = MutableLiveData<Int>()
    var albumDetails = MutableLiveData<AlbumInformation>()

    fun setIdArtist(id : Int){
        idArtist.value = id
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

}