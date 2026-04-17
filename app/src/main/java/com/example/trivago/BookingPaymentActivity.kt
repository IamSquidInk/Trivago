package com.example.trivago

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_payment)

        supportActionBar?.show()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val hotelName = intent.getStringExtra("HOTEL_NAME") ?: ""
        val hotelPrice = intent.getStringExtra("HOTEL_PRICE") ?: ""
        val hotelLocation = intent.getStringExtra("HOTEL_LOCATION") ?: ""
        val checkIn = intent.getStringExtra("CHECK_IN") ?: ""
        val checkOut = intent.getStringExtra("CHECK_OUT") ?: ""
        val guests = intent.getStringExtra("GUESTS") ?: ""
        val rooms = intent.getStringExtra("ROOMS") ?: ""

        findViewById<TextView>(R.id.tvHotelNameSummary).text = "com.example.hoteltrivago.Hotel: $hotelName"
        findViewById<TextView>(R.id.tvDatesSummary).text = "Dates: $checkIn → $checkOut | Guests: $guests | Rooms: $rooms"
        findViewById<TextView>(R.id.tvTotalPrice).text = "Total: $hotelPrice"


        val etGuestName = findViewById<EditText>(R.id.etGuestName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etCardNumber = findViewById<EditText>(R.id.etCardNumber)
        val etExpiry = findViewById<EditText>(R.id.etExpiry)
        val etCVV = findViewById<EditText>(R.id.etCVV)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)

        findViewById<Button>(R.id.btnConfirmBooking).setOnClickListener {
            val name = etGuestName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val card = etCardNumber.text.toString().trim()
            val expiry = etExpiry.text.toString().trim()
            val cvv = etCVV.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || card.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bookingRef = "BK${(100000..999999).random()}"

            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("GUEST_NAME", name)
            intent.putExtra("HOTEL_NAME", hotelName)
            intent.putExtra("HOTEL_LOCATION", hotelLocation)
            intent.putExtra("CHECK_IN", checkIn)
            intent.putExtra("CHECK_OUT", checkOut)
            intent.putExtra("GUESTS", guests)
            intent.putExtra("TOTAL_PRICE", hotelPrice)
            intent.putExtra("BOOKING_REF", bookingRef)
            intent.putExtra("ROOMS", rooms)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}