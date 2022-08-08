package com.example.toppopapp.ui

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {

    /*private var _binding: FragmentAlbumDetailsBinding? = null
    private val albumViewModel : AlbumDetailsViewModel by activityViewModels()
    lateinit var  sharedPref : SharedPreferences

    private var artistID: Int = 0
    private var idAlbum: Long = 0
    private val binding get() = _binding!!
    private var songs : String = ""
    private lateinit var db : AlbumRoomDatabase
    private lateinit var albumDao : AlbumDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        sharedPref = activity?.getSharedPreferences("MyPref", Context.MODE_PRIVATE)!!
        artistID = sharedPref.getInt("artistID", artistID)
        idAlbum = sharedPref.getLong("albumID", idAlbum)

        setHasOptionsMenu(false)
        initDatabase()
        setLiveDataListeners()

        albumViewModel.getAlbumTracks()
        albumViewModel.getAlbumDetails()

        return binding.root
    }

    private fun initDatabase(){
        db = Room.databaseBuilder(
            requireContext(),
            AlbumRoomDatabase::class.java, "album-database"
        ).allowMainThreadQueries().build()
        albumDao = db.albumDao()
    }

    private fun setLiveDataListeners(){
        albumViewModel.albumTracks.observe(viewLifecycleOwner) {
            setTrackInformation(it)
        }

        albumViewModel.albumDetails.observe(viewLifecycleOwner){
            setInformation(it)
        }
    }

    private fun setTrackInformation(data: AlbumDetails){
        val listOfSongs = ArrayList<String>()
        val songsNumber = data.numberOfTracks-1
        for (i in 0..songsNumber){
            var song = data.tracks.data[i].title + " "
            listOfSongs.add(song)
        }
        songs = listOfSongs.toString().replace("[", "").replace("]", "");
    }

    private fun setInformation(body: Tracks){
        setDatabaseInformation(body)
        val album = albumDao.findAlbumById(idAlbum.toString())
        val coverUrl : String? = album.coverUrl
        Picasso.get().load(coverUrl).into(binding.cover)
        binding.albumArtistName.text = album.artistName
        binding.albumName.text = album.albumName
        binding.albumSongName.text = album.albumSongName
        binding.songs.text = album.songsList
    }

    private fun setDatabaseInformation(body: Tracks){
        val album = Album(
            albumID = idAlbum,
            albumName = body.data[artistID].album.title,
            artistName = body.data[artistID].artist.name,
            albumSongName = body.data[artistID].title,
            coverUrl = body.data[artistID].album.cover_medium,
            songsList = songs
        )
        albumDao.insertAlbum(album)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }*/
}