package com.example.toppopapp.data.entities

import androidx.room.Entity

@Entity(tableName = "artistAndSong", primaryKeys = ["songId", "id"])
data class SongArtistDetailsCrossRef (
    val songId: Int,
    val id: Long,
)