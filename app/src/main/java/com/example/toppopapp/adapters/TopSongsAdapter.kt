package com.example.toppopapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.toppopapp.R
import com.example.toppopapp.TrackInformation
import com.example.toppopapp.InterfaceCard



class TopSongsAdapter(private var itemsList: List<TrackInformation>, var interfaceCard : InterfaceCard) : RecyclerView.Adapter<TopSongsAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var artistName: TextView = view.findViewById(R.id.artistName)
        var songName : TextView = view.findViewById(R.id.songName)
        var songDuration : TextView = view.findViewById(R.id.songDuration)
        var songNumber : TextView = view.findViewById(R.id.songNumber)
        var card : CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]

        holder.artistName.text = item.artistName
        holder.songName.text = item.songName
        holder.songNumber.text = item.position.toString()

        val minutes:Int = item.duration / 60
        val seconds: Int = item.duration % 60

        holder.songDuration.text = minutes.toString() + ":" + seconds.toString()

        holder.card.setOnClickListener{
            interfaceCard.onCardViewClick(it, item.position)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}
