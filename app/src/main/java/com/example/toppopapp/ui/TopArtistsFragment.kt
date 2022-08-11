package com.example.toppopapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toppopapp.R
import com.example.toppopapp.databinding.FragmentTopArtistsBinding
import com.example.toppopapp.ui.viewmodel.TopArtistsViewModel
import com.example.toppopapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TopArtistsFragment : Fragment() {

    private var _binding: FragmentTopArtistsBinding? = null
    private lateinit var adapter: ArtistsAdapter

    private val viewModel : TopArtistsViewModel by activityViewModels()
    private val binding get() = _binding!!
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    lateinit var sharedPref : SharedPreferences

    private var menuItem: MenuItem ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTopArtistsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = activity?.getSharedPreferences("MyPref", Context.MODE_PRIVATE) ?: return
        swipeRefreshLayout = binding.swipeRefresh

        setupRecyclerView()
        setupObservers()
        initListeners()
    }

    override fun onResume() {
        viewModel.getSongs()
        super.onResume()
    }

    private fun setupRecyclerView() {
        adapter = ArtistsAdapter()
        binding.rvArtist.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArtist.adapter = adapter
    }

    private fun setupObservers(){
        viewModel.getInformation().observe(viewLifecycleOwner){
            if(it != null){
                Timber.d("success: $it")

            }else{
                Timber.d("error")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        menuItem?.icon = ContextCompat.getDrawable(requireContext(),
            R.drawable.ic_baseline_music_note_24
        )
    }

    private fun initListeners(){
        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.isRefreshing = false
            //artistsList.sortBy { it.position }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
