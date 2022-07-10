package com.example.toppopapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toppopapp.adapters.TopSongsAdapter
import com.example.toppopapp.databinding.FragmentTopArtistsBinding
import com.example.toppopapp.network.RetrofitApiCall
import com.example.toppopapp.network.model.Data
import com.example.toppopapp.viewmodel.TopArtistsViewModel


class TopArtistsFragment : Fragment(), InterfaceCard {

    private var _binding: FragmentTopArtistsBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: TopSongsAdapter
    private lateinit var artistViewModel: TopArtistsViewModel

    private lateinit var model : RetrofitApiCall
    private val binding get() = _binding!!
    private var songList : MutableList<Data> = mutableListOf()
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    lateinit var  sharedPref : SharedPreferences

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

        sharedPref = activity?.getSharedPreferences("MyPref", Context.MODE_PRIVATE) ?: return

        swipeRefreshLayout = binding.swipeRefresh
        recyclerView = binding.recyclerView
        songList.clear()
        customAdapter = TopSongsAdapter(songList, this)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

        artistViewModel = ViewModelProvider(this)[TopArtistsViewModel::class.java]
        model = RetrofitApiCall()
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

    private fun showInformation(data: List<Data>){
        songList.clear()
        songList.addAll(data)
        customAdapter.notifyDataSetChanged()
    }

    override fun onCardViewClick(view: View, position: Int, albumId: Long) {
        with (sharedPref.edit()) {
            putInt("artistID", position-1)
            putLong("albumID", albumId)
            apply()
        }
        view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


