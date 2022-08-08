package com.example.toppopapp.ui


import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.toppopapp.R
import com.example.toppopapp.data.entities.Artist


class ArtistsAdapter() : RecyclerView.Adapter<ArtistsAdapter.MyViewHolder>() {

    private val items = ArrayList<Artist>()

    fun setItems(items: ArrayList<Artist>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var artistName: TextView = view.findViewById(R.id.artistName)
        var songName : TextView = view.findViewById(R.id.songName)
        var songDuration : TextView = view.findViewById(R.id.songDuration)
        var songNumber : TextView = view.findViewById(R.id.songNumber)
        var card : CardView = view.findViewById(R.id.card_view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.artistName.text = item.artistName
        holder.songName.text = item.title
        holder.songNumber.text = item.position.toString()
        holder.songDuration.text = DateUtils.formatElapsedTime(item.duration.toLong())

    }


}