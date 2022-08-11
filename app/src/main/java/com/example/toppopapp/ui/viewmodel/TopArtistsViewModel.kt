package com.example.toppopapp.ui.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.toppopapp.data.entities.AlbumDetailsWithArtistAndSong
import com.example.toppopapp.data.entities.ArtistDetails
import com.example.toppopapp.data.entities.Data
import com.example.toppopapp.data.local.AppDatabase
import com.example.toppopapp.data.repository.ArtistRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.coroutineContext

class TopArtistsViewModel @ViewModelInject constructor(private val repository: ArtistRepository) : ViewModel() {

    fun getSongs() = viewModelScope.launch{
        try{
            var data = repository.getSongsRemote()
        } catch (e : Exception){
            Timber.d(e.toString())
            return@launch
        }

    }

    fun getInformation() = liveData {
        emitSource(repository.getSongLocal())
    }
}
