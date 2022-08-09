package com.example.toppopapp.ui


import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toppopapp.data.entities.Artist
import com.example.toppopapp.databinding.CardLayoutBinding


class ArtistsAdapter() : RecyclerView.Adapter<ArtistViewHolder>() {

    interface ArtistItemListener {
        fun onClickedArtist(characterId: Int)
    }

    private val items = ArrayList<Artist>()

    fun setItems(items: ArrayList<Artist>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding: CardLayoutBinding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) = holder.bind(items[position])
}

class ArtistViewHolder(private val itemBinding: CardLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var artist: Artist

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Artist) {
        this.artist = item
        itemBinding.artistName.text = "name"
        itemBinding.songDuration.text = DateUtils.formatElapsedTime(item.duration.toLong())
        itemBinding.songNumber.text = item.position.toString()
        itemBinding.songName.text = item.title

    }

    override fun onClick(v: View?) {
        //listener.onClickedArtist(artist.id)
    }
}