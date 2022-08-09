package com.example.toppopapp.data.repository

import com.example.toppopapp.data.local.ArtistDao
import com.example.toppopapp.data.remote.ArtistRemoteDataSource
import com.example.toppopapp.utils.performGetOperation
import javax.inject.Inject

class ArtistRepository @Inject constructor (private val remoteDataSource: ArtistRemoteDataSource, private val localDataSource: ArtistDao) {
    fun getArtist() = performGetOperation(
        databaseQuery = { localDataSource.getAllArtists() },
        networkCall = { remoteDataSource.getArtist() },
        saveCallResult = { localDataSource.insertArtists(it.data) }
    )
}