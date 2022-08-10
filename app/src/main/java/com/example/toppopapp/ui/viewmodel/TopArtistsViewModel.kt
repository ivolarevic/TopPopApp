package com.example.toppopapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.toppopapp.data.repository.ArtistRepository

class TopArtistsViewModel @ViewModelInject constructor(
    private val repository: ArtistRepository
) : ViewModel() {

    val songs = repository.getSong()
    val artist = repository.getArtist()

}
