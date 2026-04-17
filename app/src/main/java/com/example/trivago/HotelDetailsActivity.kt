package com.example.trivago

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HotelDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)

        supportActionBar?.show()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hotel_details)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val hotelName = intent.getStringExtra("HOTEL_NAME") ?: ""
        val hotelLocation = intent.getStringExtra("HOTEL_LOCATION") ?: ""
        val hotelPrice = intent.getStringExtra("HOTEL_PRICE") ?: ""
        val checkIn = intent.getStringExtra("CHECK_IN") ?: ""
        val checkOut = intent.getStringExtra("CHECK_OUT") ?: ""
        val guests = intent.getStringExtra("GUESTS") ?: ""
        val rooms = intent.getStringExtra("ROOMS") ?: ""
        val hotelDescription = intent.getStringExtra("HOTEL_DESCRIPTION") ?: ""
        val hotelRating = intent.getFloatExtra("HOTEL_RATING", 0f)
        val hotelImage = intent.getIntExtra("HOTEL_IMAGE", R.drawable.placeholder_hotel)

        findViewById<TextView>(R.id.tvHotelName).text = hotelName
        findViewById<TextView>(R.id.tvHotelLocation).text = hotelLocation
        findViewById<TextView>(R.id.tvHotelPrice).text = hotelPrice
        findViewById<TextView>(R.id.tvHotelDescription).text =
            "Experience the best of $hotelLocation."
        findViewById<TextView>(R.id.tvHotelDescription).text = hotelDescription
        findViewById<RatingBar>(R.id.rbHotelRating).rating = hotelRating
        findViewById<ImageView>(R.id.ivHotelImage).setImageResource(hotelImage)

        val prefs = getSharedPreferences("bookings", MODE_PRIVATE)
        val activeBookingRef = prefs.getString("active_booking_ref", null)
        val activeBookingHotel = prefs.getString("active_booking_hotel", null)

        val btnBookNow = findViewById<Button>(R.id.btnBookNow)
        val btnCancelBooking = findViewById<Button>(R.id.btnCancelBooking)

        if (activeBookingRef != null && activeBookingHotel == hotelName) {
            btnCancelBooking.visibility = View.VISIBLE
        } else {
            btnCancelBooking.visibility = View.GONE
        }

        btnBookNow.setOnClickListener {
            if (checkIn.isEmpty() || checkOut.isEmpty() || guests.isEmpty()) {
                Toast.makeText(this, "Please fill in check-in, check-out and guests first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, BookingPaymentActivity::class.java)
            intent.putExtra("HOTEL_NAME", hotelName)
            intent.putExtra("HOTEL_PRICE", hotelPrice)
            intent.putExtra("HOTEL_LOCATION", hotelLocation)
            intent.putExtra("CHECK_IN", checkIn)
            intent.putExtra("CHECK_OUT", checkOut)
            intent.putExtra("GUESTS", guests)
            startActivity(intent)
        }

        btnCancelBooking.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cancel Booking")
                .setMessage("Are you sure you want to cancel booking $activeBookingRef for $activeBookingHotel?")
                .setPositiveButton("Yes, Cancel") { _, _ ->
                    prefs.edit().clear().apply()
                    btnCancelBooking.visibility = View.GONE
                    Toast.makeText(this, "Booking $activeBookingRef has been cancelled.", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No, Keep It", null)
                .show()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}