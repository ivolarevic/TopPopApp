package com.example.toppopapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.toppopapp.data.entities.AlbumDetails

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album")
    fun getAlbum(): LiveData<List<AlbumDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(tracks: List<AlbumDetails>)

}