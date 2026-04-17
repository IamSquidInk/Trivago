package com.example.trivago

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
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
        val tvTotal: TextView = itemView.findViewById(R.id.tvHotelItemTotal)
        val tvAmenities: TextView = itemView.findViewById(R.id.tvHotelItemAmenities)
        val rbRating: RatingBar = itemView.findViewById(R.id.rbHotelItemRating)
        val ivImage: ImageView = itemView.findViewById(R.id.ivHotelItemImage)
        val btnBook: Button = itemView.findViewById(R.id.btnHotelItemBook)
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
        holder.tvAmenities.text = hotel.amenities.joinToString(" · ")
        holder.rbRating.rating = hotel.rating
        holder.ivImage.setImageResource(hotel.imageRes)

        // Total price will be calculated based on nights
        holder.tvTotal.text = "Select dates for total price"

        holder.btnBook.setOnClickListener { onClick(hotel) }
        holder.itemView.setOnClickListener { onClick(hotel) }
    }

    override fun getItemCount() = hotels.size
}