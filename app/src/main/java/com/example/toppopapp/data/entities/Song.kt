package com.example.toppopapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.toppopapp.data.local.AppDatabase
import com.google.gson.annotations.SerializedName

@Entity(tableName = AppDatabase.TablesInfo.TABLE_SONG)
data class Song(
    @PrimaryKey
    val songId: Int,
    val title: String,
    val position : Int,
    val duration : Int,
)
