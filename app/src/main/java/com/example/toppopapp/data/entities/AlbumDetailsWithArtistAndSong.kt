package com.example.toppopapp.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class AlbumDetailsWithArtistAndSong(
    @Embedded val albumDetails: AlbumDetails,
    @Relation(
        entity = ArtistDetails::class,
        parentColumn = "id",
        entityColumn = "id"
    )
    val songWithArtistDetails : List<SongWithArtistDetails>
)
