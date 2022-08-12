package com.example.toppopapp.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.toppopapp.data.local.AppDatabase
import com.google.gson.annotations.SerializedName

@Entity(tableName = AppDatabase.TablesInfo.TABLE_ARTIST)
data class ArtistDetails(
    @PrimaryKey
    val id: Long,
    val name: String?,
)
