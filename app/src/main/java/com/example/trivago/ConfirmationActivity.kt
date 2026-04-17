package com.example.trivago

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.confirmation)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val guestName = intent.getStringExtra("GUEST_NAME") ?: ""
        val hotelName = intent.getStringExtra("HOTEL_NAME") ?: ""
        val dateRange = intent.getStringExtra("DATE_RANGE") ?: ""
        val guests = intent.getStringExtra("GUESTS") ?: ""
        val totalPrice = intent.getStringExtra("TOTAL_PRICE") ?: ""
        val bookingRef = intent.getStringExtra("BOOKING_REF") ?: ""
        val rooms = intent.getStringExtra("ROOMS") ?: ""

        // Save booking to SharedPreferences
        val prefs = getSharedPreferences("bookings", MODE_PRIVATE)
        prefs.edit()
            .putString("active_booking_ref", bookingRef)
            .putString("active_booking_hotel", hotelName)
            .putString("active_booking_checkin", dateRange)
            .putString("active_booking_guests", guests)
            .putString("active_booking_rooms", rooms)
            .putString("active_booking_price", totalPrice)
            .putString("active_booking_guest_name", guestName)
            .apply()

        findViewById<TextView>(R.id.tvBookingRef).text = "Booking Ref: $bookingRef"
        findViewById<TextView>(R.id.tvConfirmationDetails).text =
            "Guest: $guestName\nHotel: $hotelName\n" +
                    "Dates: $dateRange\n" +
                    "Guests: $guests | Rooms: $rooms\nTotal: $totalPrice"

        findViewById<Button>(R.id.btnBackHome).setOnClickListener {
            val intent = Intent(this, SearchResultsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}

