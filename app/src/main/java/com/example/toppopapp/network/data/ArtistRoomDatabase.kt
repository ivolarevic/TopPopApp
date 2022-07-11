package com.example.toppopapp.network.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Artist::class], version = 1)
abstract class ArtistRoomDatabase : RoomDatabase(){
    abstract fun artistDao() : ArtistDao
}

