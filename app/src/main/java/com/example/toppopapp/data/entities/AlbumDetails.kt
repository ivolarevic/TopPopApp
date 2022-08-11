package com.example.toppopapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.toppopapp.data.local.AppDatabase
import com.google.gson.annotations.SerializedName

@Entity(tableName = AppDatabase.TablesInfo.TABLE_ALBUM)
data class AlbumDetails(
    @PrimaryKey
    val id: Long,
    val title: String,
    val cover: String,
)
