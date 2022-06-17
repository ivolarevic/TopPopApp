package com.example.toppopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toppopapp.network.RequestCompleteListener
import com.example.toppopapp.network.TracksModel
import com.example.toppopapp.network.model.Tracks

class TopArtistsViewModel : ViewModel() {

    val popularSongsLiveData = MutableLiveData<Tracks>()

    fun getPopularSongsList(model: TracksModel) {
        model.getTopSongs(object : RequestCompleteListener<Tracks>{
            override fun onRequestSuccess(tracks: Tracks){
                popularSongsLiveData.postValue(tracks)
            }
            override fun onRequestFailed(errorMessage: String) {
            }
        })
    }
}
