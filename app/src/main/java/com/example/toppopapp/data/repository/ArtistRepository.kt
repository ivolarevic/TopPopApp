package com.example.toppopapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.toppopapp.data.entities.*
import com.example.toppopapp.data.local.AlbumDao
import com.example.toppopapp.data.local.ArtistDao
import com.example.toppopapp.data.local.SongDao
import com.example.toppopapp.data.remote.ArtistRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ArtistRepository @Inject constructor (private val remoteDataSource: ArtistRemoteDataSource, private val songDao: SongDao, private val artistDao: ArtistDao, private val albumDao: AlbumDao) {
    suspend fun getSongsRemote(){
        val apiCall = remoteDataSource.getSong()
        val artist : MutableList<ArtistDetails> = mutableListOf()
        val song : MutableList<Song> = mutableListOf()
        val album : MutableList<AlbumDetails> = mutableListOf()
        val artistAndSong : MutableList<SongArtistDetailsCrossRef> = mutableListOf()

        for(data in apiCall.data) {
            artist.add(ArtistDetails(
                id = data.artist.id,
                name = data.artist.name
            ))
            Timber.tag("artist").d(data.artist.name)

            album.add(data.album)
            song.add(
                Song(
                    data.id,
                    data.title,
                    data.position,
                    data.duration,
                )
            )
            Timber.d("id: ${data.artist.id}")
            artistAndSong.add(
                SongArtistDetailsCrossRef(
                    songId = data.id,
                    id = data.artist.id,
                )
            )

        }
        // COROUTINE
        //appDatabase.runInTransaction {
            CoroutineScope(Dispatchers.IO).launch {
                artistDao.insertArtist(artist)
                albumDao.insertAlbum(album)
                songDao.insertSongs(song)
                songDao.insertSongsWithArtist(artistAndSong)
            }
        //}
    }

    fun getSongLocal() : LiveData<List<ArtistDetailsWithSong>> = songDao.getArtistWithSong()
}
