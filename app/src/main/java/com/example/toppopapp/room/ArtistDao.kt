package com.example.toppopapp.room

import androidx.room.*

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist")
    fun getAllArtists(): MutableList<Artist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(artist: MutableList<Artist>)
}