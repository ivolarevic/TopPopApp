package com.example.toppopapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.toppopapp.data.entities.Artist

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist")
    fun getAllArtists(): LiveData<List<Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(artist: List<Artist>)

    @Query("DELETE FROM artist")
    fun deleteTopArtists()
}
