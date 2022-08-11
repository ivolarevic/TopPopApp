package com.example.toppopapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.toppopapp.data.entities.AlbumDetails
import com.example.toppopapp.data.entities.AlbumDetailsWithArtistAndSong
import com.example.toppopapp.data.entities.Data

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album")
    fun getAlbum(): LiveData<List<AlbumDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(tracks: List<AlbumDetails>)

    @Transaction
    @Query("SELECT * FROM album")
    fun getAlbumWithArtistAndSong(): LiveData<List<AlbumDetailsWithArtistAndSong>>



}