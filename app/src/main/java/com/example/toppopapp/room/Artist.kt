package com.example.toppopapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artist (
    @PrimaryKey
    val title: String,
    @ColumnInfo(name = "artistID") val artistID : Long,
    @ColumnInfo(name = "position") val position : Int,
    @ColumnInfo(name = "artist_name") val artistName : String?,
    @ColumnInfo(name = "album_ID") val albumID : Long,
    @ColumnInfo(name = "duration") val duration : Int,

)