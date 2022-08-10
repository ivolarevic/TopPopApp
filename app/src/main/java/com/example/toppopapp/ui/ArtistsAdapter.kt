package com.example.toppopapp.ui


import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toppopapp.data.entities.Artist
import com.example.toppopapp.data.entities.Song
import com.example.toppopapp.databinding.CardLayoutBinding


class ArtistsAdapter() : RecyclerView.Adapter<ArtistViewHolder>() {
    private val songItems = ArrayList<Song>()
    private val artistItems = ArrayList<Artist>()

    fun setArtistItems(items: ArrayList<Artist>) {
        this.artistItems.clear()
        this.artistItems.addAll(items)
        notifyDataSetChanged()
    }

    fun setSongItems(items: ArrayList<Song>) {
        this.songItems.clear()
        this.songItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding: CardLayoutBinding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun getItemCount(): Int = artistItems.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) = holder.bind(artistItems[position])
}

class ArtistViewHolder(private val itemBinding: CardLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var song: Song
    private lateinit var artist: Artist

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Artist) {
        this.artist = item
        itemBinding.artistName.text = item.name

    }

    fun bind(item: Song) {
        this.song = item
        itemBinding.songDuration.text = DateUtils.formatElapsedTime(item.duration.toLong())
        itemBinding.songNumber.text = item.position.toString()
        itemBinding.songName.text = item.title
    }


    override fun onClick(v: View?) {
        //listener.onClickedArtist(artist.id)
    }
}