package com.example.toppopapp.ui


import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toppopapp.data.entities.ArtistDetailsWithSong
import com.example.toppopapp.data.entities.Data
import com.example.toppopapp.databinding.CardLayoutBinding


class ArtistsAdapter : RecyclerView.Adapter<ArtistViewHolder>() {
    private val dataItems = ArrayList<ArtistDetailsWithSong>()

    fun setSongItems(items: ArrayList<ArtistDetailsWithSong>) {
        this.dataItems.clear()
        this.dataItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding: CardLayoutBinding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun getItemCount(): Int = dataItems.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) = holder.bind(dataItems[position], position)
}

class ArtistViewHolder(private val itemBinding: CardLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var data: ArtistDetailsWithSong

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: ArtistDetailsWithSong, position: Int) {
        this.data = item
        itemBinding.artistName.text = item.artistDetails.name
        itemBinding.songDuration.text = DateUtils.formatElapsedTime(item.song.duration.toLong())
        itemBinding.songNumber.text = item.song.position.toString()
        itemBinding.songName.text = item.song.title
    }

    override fun onClick(v: View?) {
        //listener.onClickedArtist(artist.id)
    }
}