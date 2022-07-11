package com.example.toppopapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Album::class], version = 1)
abstract class AlbumRoomDatabase : RoomDatabase(){
    abstract fun albumDao() : AlbumDao
}

