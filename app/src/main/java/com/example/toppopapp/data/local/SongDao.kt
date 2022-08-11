package com.example.toppopapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.toppopapp.data.entities.Data
import com.example.toppopapp.data.entities.Song
import com.example.toppopapp.data.entities.SongArtistDetailsCrossRef
import com.example.toppopapp.data.entities.SongWithArtistDetails

@Dao
interface SongDao {
    @Query("SELECT * FROM song")
    fun getAllSongs(): LiveData<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(tracks: List<Song>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongsWithArtist(tracks: List<SongArtistDetailsCrossRef>)

    @Query("SELECT * FROM artistAndSong")
    fun getArtistWithSong(): LiveData<List<SongWithArtistDetails>>

    @Query("DELETE FROM song")
    fun deleteSongs()

}
