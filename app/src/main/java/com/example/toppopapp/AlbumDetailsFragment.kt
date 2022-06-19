package com.example.toppopapp

import android.R
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.toppopapp.databinding.FragmentAlbumDetailsBinding
import com.example.toppopapp.network.RetrofitApiAlbumCall
import com.example.toppopapp.network.RetrofitApiCall
import com.example.toppopapp.network.data.AlbumInformation
import com.example.toppopapp.network.model.AlbumDetails
import com.example.toppopapp.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso


class AlbumDetailsFragment : Fragment() {

    private var _binding: FragmentAlbumDetailsBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var model : RetrofitApiCall
    private lateinit var modelTracks :  RetrofitApiAlbumCall

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)

        setHasOptionsMenu(false)
        setLiveDataListeners()

        model =  RetrofitApiCall(requireContext())
        modelTracks = RetrofitApiAlbumCall(requireContext())

        sharedViewModel.getAlbumTracks(modelTracks)
        sharedViewModel.getAlbumDetails(model)

        return binding.root
    }

    private fun setLiveDataListeners(){
        sharedViewModel.idAlbum.observe(viewLifecycleOwner, Observer{
        })

        sharedViewModel.idArtist.observe(viewLifecycleOwner, Observer {
        })

        sharedViewModel.albumDetails.observe(viewLifecycleOwner, Observer{
            setInformation(it)
        })

        sharedViewModel.albumTracks.observe(viewLifecycleOwner, Observer{
            setTrackInformation(it)
        })
    }

    private fun setInformation(data : AlbumInformation){
        binding.albumArtistName.text = data.artistName
        binding.albumName.text = data.albumName
        binding.albumSongName.text = data.songName
        val coverUrl : String = data.cover
        Picasso.get().load(coverUrl).into(binding.cover)
    }

    private fun setTrackInformation(data: AlbumDetails){
        val listOfSongs = ArrayList<String>()
        val songsNumber = data.numberOfTracks-1

        for (i in 0..songsNumber){
            var song = data.tracks.data[i].title + " "
            listOfSongs.add(song)
        }
        binding.songs.text = listOfSongs.toString().replace("[", "").replace("]", "");
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}