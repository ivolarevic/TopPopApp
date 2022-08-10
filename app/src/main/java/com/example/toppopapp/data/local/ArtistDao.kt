package com.example.toppopapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.toppopapp.data.entities.Artist
import com.example.toppopapp.data.entities.Song

@Dao
interface ArtistDao {
    @Query("SELECT * FROM song")
    fun getAllSongs(): LiveData<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(song: List<Song>)

    @Query("DELETE FROM song")
    fun deleteSongs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: List<Artist>)

    @Query("SELECT * FROM artist")
    fun getAllArtists(): LiveData<List<Artist>>

}
