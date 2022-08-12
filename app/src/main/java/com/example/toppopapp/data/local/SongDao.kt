package com.example.toppopapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.toppopapp.data.entities.ArtistDetailsWithSong
import com.example.toppopapp.data.entities.Song
import com.example.toppopapp.data.entities.SongArtistDetailsCrossRef

@Dao
interface SongDao {
    @Query("SELECT * FROM song")
    fun getAllSongs(): LiveData<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(tracks: List<Song>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongsWithArtist(tracks: List<SongArtistDetailsCrossRef>)

    @Transaction
    @Query("SELECT * FROM song")
    fun getArtistWithSong(): LiveData<List<ArtistDetailsWithSong>>

    @Query("DELETE FROM song")
    fun deleteSongs()

}
