package com.example.toppopapp.network.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist")
    fun getAllArtists(): List<Artist>

    @Query("SELECT * FROM artist WHERE title LIKE (:title) LIMIT 1")
    fun findArtist(title: String): Artist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(vararg artist: Artist)
}