package com.example.toppopapp.adapters


import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.toppopapp.R
import com.example.toppopapp.InterfaceCard
import com.example.toppopapp.room.Artist



class TopSongsAdapter(private var itemsList: MutableList<Artist>, var interfaceCard : InterfaceCard) : RecyclerView.Adapter<TopSongsAdapter.MyViewHolder>() {
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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.artistName.text = item.artistName
        holder.songName.text = item.title
        holder.songNumber.text = item.position.toString()
        holder.songDuration.text = DateUtils.formatElapsedTime(item.duration.toLong())
        holder.card.setOnClickListener{
            interfaceCard.onCardViewClick(it, item.position, item.albumID)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}