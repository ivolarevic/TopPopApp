package com.example.toppopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toppopapp.network.ApiModel
import com.example.toppopapp.network.RequestCompleteListener
import com.example.toppopapp.network.model.Data
import com.example.toppopapp.network.model.Tracks

class TopArtistsViewModel : ViewModel() {

    val popularSongsLiveData = MutableLiveData<Tracks>()

    fun getPopularSongsList(model: ApiModel) {
        model.getTopSongs(object : RequestCompleteListener<Tracks>{
            override fun onRequestSuccess(tracks: Tracks){
                popularSongsLiveData.postValue(tracks)
            }
            override fun onRequestFailed(errorMessage: String) {
            }
        })
    }
}
