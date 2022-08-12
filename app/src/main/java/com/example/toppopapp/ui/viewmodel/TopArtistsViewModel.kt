package com.example.toppopapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.toppopapp.data.repository.ArtistRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class TopArtistsViewModel @ViewModelInject constructor(private val repository: ArtistRepository) : ViewModel() {

    fun getSongs() = viewModelScope.launch{
        try{
            repository.getSongsRemote()
        } catch (e : Exception){
            Timber.d(e.toString())
            return@launch
        }

    }

    fun getInformation() = liveData {
        emitSource(repository.getSongLocal())
    }
}
