package com.example.toppopapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.toppopapp.data.entities.*

@Database(entities = [Song::class, ArtistDetails::class, AlbumDetails::class, SongArtistDetailsCrossRef::class],  version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    class TablesInfo {
        companion object{
            const val TABLE_SONG = "song"
            const val TABLE_ARTIST = "artist"
            const val TABLE_ALBUM = "album"
        }
    }

    abstract fun artistDao(): ArtistDao
    abstract fun albumDao(): AlbumDao
    abstract fun songDao(): SongDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "artist")
                .fallbackToDestructiveMigration()
                .build()
    }

}