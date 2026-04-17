package com.example.trivago

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(
    private val hotels: List<Hotel>,
    private val onClick: (Hotel) -> Unit
) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvHotelItemName)
        val tvLocation: TextView = itemView.findViewById(R.id.tvHotelItemLocation)
        val tvPrice: TextView = itemView.findViewById(R.id.tvHotelItemPrice)
        val tvRating: TextView = itemView.findViewById(R.id.tvHotelItemRating)
        val ivImage: ImageView = itemView.findViewById(R.id.ivHotelItemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.tvName.text = hotel.name
        holder.tvLocation.text = hotel.location
        holder.tvPrice.text = hotel.price
        holder.tvRating.text = "⭐ ${hotel.rating}"
        holder.ivImage.setImageResource(hotel.imageRes)
        holder.itemView.setOnClickListener { onClick(hotel) }
    }

    override fun getItemCount() = hotels.size
}