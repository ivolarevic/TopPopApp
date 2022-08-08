package com.example.toppopapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class Artist (
    @PrimaryKey
    val title: String,
    val artistID : Long,
    val position : Int,
    val artistName : String?,
    val albumID : Long,
    val duration : Int,

    )