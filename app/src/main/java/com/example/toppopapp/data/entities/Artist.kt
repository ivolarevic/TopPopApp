package com.example.toppopapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class Artist (
    @PrimaryKey
    val id: Int,
    val title: String,
    val position : Int,
    val albumID : Long,
    val duration : Int,
)