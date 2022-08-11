package com.example.toppopapp.data.remote

import javax.inject.Inject

class ArtistRemoteDataSource @Inject constructor(private val artistService: ArtistService): BaseDataSource() {
    suspend fun getSong() = artistService.getTopSongs()
}
