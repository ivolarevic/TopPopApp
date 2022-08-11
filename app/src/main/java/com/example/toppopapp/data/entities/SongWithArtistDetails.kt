package com.example.toppopapp.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class SongWithArtistDetails (
    @Embedded val artistDetails: ArtistDetails,
    @Relation(
        parentColumn = "id",
        entityColumn = "songId",
        associateBy = Junction(SongArtistDetailsCrossRef::class)
    )
    val song: Song

)