package com.example.toppopapp

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toppopapp.adapters.TopSongsAdapter
import com.example.toppopapp.databinding.FragmentTopArtistsBinding
import com.example.toppopapp.network.RetrofitApiCall
import com.example.toppopapp.network.data.TrackInformation
import com.example.toppopapp.network.model.Tracks
import com.example.toppopapp.viewmodel.SharedViewModel
import com.example.toppopapp.viewmodel.TopArtistsViewModel
import com.google.android.material.appbar.MaterialToolbar


class TopArtistsFragment : Fragment(), InterfaceCard {

    private var _binding: FragmentTopArtistsBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: TopSongsAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var artistViewModel: TopArtistsViewModel

    private lateinit var model : RetrofitApiCall

    private val binding get() = _binding!!
    private var songList = ArrayList<TrackInformation>()
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    // Flags for sorting
    private var sortAsc:Boolean = false
    private var sortDesc:Boolean = false
    private var sortNorm : Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTopArtistsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = binding.swipeRefresh
        recyclerView = binding.recyclerView
        songList.clear()
        customAdapter = TopSongsAdapter(songList, this)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

        artistViewModel = ViewModelProvider(this)[TopArtistsViewModel::class.java]
        model = RetrofitApiCall(requireContext())
        artistViewModel.getPopularSongsList(model)

        setLiveDataListeners()
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    private fun setLiveDataListeners(){
        artistViewModel.popularSongsLiveData.observe(viewLifecycleOwner, Observer { it
            showInformation(it)
        })
    }

    private fun initListeners(){
        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.isRefreshing = false
            songList.sortBy { it.position }
            customAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
                R.id.action_asc -> sortAsc = true
                R.id.action_desc -> sortDesc = true
                R.id.action_normal -> sortNorm = true
            else -> super.onOptionsItemSelected(item)
        }
        sortSongs()
        return true
    }

    private fun sortSongs(){
        if(sortAsc){
            songList.sortBy { it.duration }
            sortAsc = false
        }else if(sortDesc){
            songList.sortByDescending { it.duration }
            sortDesc = false
        }else if(sortNorm){
            songList.sortBy { it.position }
            sortNorm = false
        }
        customAdapter.notifyDataSetChanged()
    }

    private fun showInformation(body: Tracks){
        songList.clear()
        val numberOfArtists = body.total - 1
        for (i in 0..numberOfArtists){
            val position = body.data[i].position
            val songName = body.data[i].title
            val artistName = body.data[i].artist.name
            val duration = body.data[i].duration
            val albumId = body.data[i].album.id

            songList.add(TrackInformation(position,songName,artistName,duration,albumId))
        }
        customAdapter.notifyDataSetChanged()
    }

    override fun onCardViewClick(view: View, position: Int, albumId: Long) {
        view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        sharedViewModel.setIdArtist(position-1)
        sharedViewModel.setIdAlbum(albumId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}