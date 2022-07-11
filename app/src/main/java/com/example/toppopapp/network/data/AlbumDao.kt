package com.example.toppopapp.network.data

import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album WHERE albumID LIKE (:albumID) LIMIT 1")
    fun findAlbumById(albumID: String): Album

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(vararg album: Album)

    @Delete
    fun delete(album: Album)

}