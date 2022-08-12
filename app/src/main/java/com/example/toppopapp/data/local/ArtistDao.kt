package com.example.toppopapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.toppopapp.data.entities.ArtistDetails

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist")
    fun getArtist(): LiveData<List<ArtistDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(tracks: List<ArtistDetails>)

}