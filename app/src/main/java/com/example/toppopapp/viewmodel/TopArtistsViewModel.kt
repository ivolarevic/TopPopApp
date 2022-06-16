package com.example.toppopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toppopapp.network.RequestCompleteListener
import com.example.toppopapp.network.TracksModel
import com.example.toppopapp.network.model.Tracks

class TopArtistsViewModel : ViewModel() {

    val popularSongsLiveData = MutableLiveData<Tracks>()
    //var _popularSongs = MutableLiveData<Tracks>()

    fun getPopularSongsList(model: TracksModel) {
        model.getTopSongs(object : RequestCompleteListener<Tracks>{
            override fun onRequestSuccess(tracks: Tracks){
                /*val popularSongsList = TrackInformation(
                    songName = tracks.data[0].title,
                    artistName = tracks.data[0].artist.name,
                    duration = tracks.data[0].duration,
                    position = tracks.data[0].position,
                )*/

                //_popularSongs.get(0).data[0].artist
                popularSongsLiveData.postValue(tracks)
                //popularSongsLiveData.value!!.data[0].artist.name
            }
            override fun onRequestFailed(errorMessage: String) {
                //popularSongsLiveData.postValue(errorMessage)
            }
        })
    }
}
