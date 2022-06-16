package com.example.toppopapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toppopapp.adapters.TopSongsAdapter
import com.example.toppopapp.databinding.FragmentFirstBinding
import com.example.toppopapp.network.RetrofitService
import com.example.toppopapp.network.model.Tracks
import com.example.toppopapp.viewmodel.FirstFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.findNavController
import com.example.toppopapp.viewmodel.SharedViewModel


class TopArtists : Fragment(), InterfaceCard {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: TopSongsAdapter
    private lateinit var viewModel: FirstFragmentViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val binding get() = _binding!!
    private var apiInterface: Call<Tracks>?= null
    private var songList = ArrayList<TrackInformation>()
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    private val NUM_SONGS = 9

    private var sortAsc:Boolean = false
    private var sortDesc:Boolean = false
    private var sortNorm : Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
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

        initListeners()
        fetchApi()

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

    private fun initListeners(){
        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.isRefreshing = false
            songList.clear()
            fetchApi()
        }
    }

    // move to firstfragmentviewmodel
    private fun fetchApi(){
        apiInterface = RetrofitService.create().getTopSongs()
        apiInterface!!.enqueue(object : Callback<Tracks> {
            override fun onResponse(call: Call<Tracks>, response: Response<Tracks>) {
                if (response.isSuccessful) {
                    for (i in 0..NUM_SONGS){
                        showInformation(response.body()!!, i)
                    }
                }
            }
            override fun onFailure(call: Call<Tracks>, t: Throwable) {
            }
        })
    }

    private fun showInformation(body: Tracks, i: Int){
        val position = body.data[i].position
        val songName = body.data[i].title
        val artistName = body.data[i].artist.name
        val duration = body.data[i].duration

        songList.add(TrackInformation(position,songName,artistName,duration))
        customAdapter.notifyDataSetChanged()

    }

    override fun onCardViewClick(view: View, position: Int) {
        view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        sharedViewModel.setIdArtist(position-1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}