package com.example.toppopapp.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ArtistDetailsWithSong (
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "id",
        associateBy = Junction(SongArtistDetailsCrossRef::class)
    )
    val artistDetails: ArtistDetails

)