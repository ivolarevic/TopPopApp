package com.example.toppopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.toppopapp.databinding.FragmentSecondBinding
import com.example.toppopapp.network.RetrofitApiCall
import com.example.toppopapp.network.data.AlbumInformation
import com.example.toppopapp.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso

class AlbumDetailsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var model : RetrofitApiCall

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setHasOptionsMenu(false)

        setLiveDataListeners()
        model =  RetrofitApiCall(requireContext())
        sharedViewModel.getAlbumDetails(model)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setLiveDataListeners(){
        sharedViewModel.idArtist.observe(viewLifecycleOwner, Observer { it
        })

        sharedViewModel.albumDetails.observe(viewLifecycleOwner, Observer{
            setInformation(it)
        })
    }

    private fun setInformation(data : AlbumInformation){
        binding.albumArtistName.text = data.artistName
        binding.albumName.text = data.albumName
        binding.albumSongName.text = data.songName
        val coverUrl : String = data.cover

        Picasso.get().load(coverUrl).into(binding.cover)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}