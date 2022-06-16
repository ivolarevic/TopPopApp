package com.example.toppopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toppopapp.TrackInformation
import com.example.toppopapp.network.RequestCompleteListener
import com.example.toppopapp.network.TracksModel
import com.example.toppopapp.network.model.Tracks

class FirstFragmentViewModel : ViewModel() {

    val popularSongsLiveData = MutableLiveData<TrackInformation>()

    fun getPopularSongsList(model: TracksModel) {
        for (i in 0..9){
            model.getTopSongs(object : RequestCompleteListener<Tracks>{
                override fun onRequestSuccess(tracks: Tracks){
                    val popularSongsList = TrackInformation(
                        songName = tracks.data[i].title,
                        artistName = tracks.data[i].artist.name,
                        duration = tracks.data[i].duration,
                        position = tracks.data[i].position,
                    )
                    popularSongsLiveData.postValue(popularSongsList)
                }
                override fun onRequestFailed(errorMessage: String) {
                    //popularSongsLiveData.postValue(errorMessage)
                }
            })
        }

    }
}
