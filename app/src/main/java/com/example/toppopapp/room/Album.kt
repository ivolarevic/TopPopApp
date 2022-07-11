package com.example.toppopapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @PrimaryKey val albumID : Long,
    @ColumnInfo(name = "artist_name") val artistName : String?,
    @ColumnInfo(name = "album_name") val albumName : String?,
    @ColumnInfo(name = "album_song_name") val albumSongName : String?,
    @ColumnInfo(name = "cover_url") val coverUrl : String?,
    @ColumnInfo(name = "songs_list") val songsList : String
)
