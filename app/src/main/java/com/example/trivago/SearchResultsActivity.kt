package com.example.trivago

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.DatePickerDialog
import java.util.Calendar

class SearchResultsActivity : AppCompatActivity() {

    private var currentHotels: List<Hotel> = HotelData.hotels["cebu"] ?: emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.search_results)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etDestination = findViewById<EditText>(R.id.etDestination)
        val etCheckIn = findViewById<EditText>(R.id.etCheckIn)
        val etCheckOut = findViewById<EditText>(R.id.etCheckOut)
        val etGuests = findViewById<EditText>(R.id.etGuests)
        val etRooms = findViewById<EditText>(R.id.etRooms)
        val tvResultsHeader = findViewById<TextView>(R.id.tvResultsHeader)
        val rvHotels = findViewById<RecyclerView>(R.id.rvHotels)

        etCheckIn.isFocusable = false
        etCheckIn.isClickable = true
        etCheckIn.setOnClickListener {
            showDatePicker { date -> etCheckIn.setText(date) }
        }

        etCheckOut.isFocusable = false
        etCheckOut.isClickable = true
        etCheckOut.setOnClickListener {
            showDatePicker { date -> etCheckOut.setText(date) }
        }

        rvHotels.layoutManager = LinearLayoutManager(this)
        loadHotels(rvHotels, etCheckIn, etCheckOut, etGuests, etRooms)

        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            val destination = etDestination.text.toString().trim()
            val checkIn = etCheckIn.text.toString().trim()
            val checkOut = etCheckOut.text.toString().trim()
            val guests = etGuests.text.toString().trim()
            val rooms = etRooms.text.toString().trim()

            if (destination.isEmpty()) {
                Toast.makeText(this, "Please enter a destination", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (checkIn.isEmpty()) {
                Toast.makeText(this, "Please select a check-in date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (checkOut.isEmpty()) {
                Toast.makeText(this, "Please select a check-out date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (guests.isEmpty()) {
                Toast.makeText(this, "Please enter number of guests", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (rooms.isEmpty()) {
                Toast.makeText(this, "Please enter number of rooms", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val key = destination.lowercase().trim()

            currentHotels = HotelData.hotels.entries
                .filter { it.key.contains(key) || key.contains(it.key) }
                .flatMap { it.value }

            if (currentHotels.isEmpty()) {
                Toast.makeText(this, "No hotels found for \"$destination\"", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tvResultsHeader.text = "Hotels in $destination"
            loadHotels(rvHotels, etCheckIn, etCheckOut, etGuests, etRooms)
        }
    }

    private fun loadHotels(
        rvHotels: RecyclerView,
        etCheckIn: EditText,
        etCheckOut: EditText,
        etGuests: EditText,
        etRooms: EditText
    ) {
        rvHotels.adapter = HotelAdapter(currentHotels) { hotel ->
            val checkIn = etCheckIn.text.toString().trim()
            val checkOut = etCheckOut.text.toString().trim()
            val guests = etGuests.text.toString().trim()
            val rooms = etRooms.text.toString().trim()

            val intent = Intent(this, HotelDetailsActivity::class.java)
            intent.putExtra("HOTEL_NAME", hotel.name)
            intent.putExtra("HOTEL_LOCATION", hotel.location)
            intent.putExtra("HOTEL_PRICE", hotel.price)
            intent.putExtra("HOTEL_DESCRIPTION", hotel.description)
            intent.putExtra("HOTEL_RATING", hotel.rating)
            intent.putExtra("HOTEL_IMAGE", hotel.imageRes)
            intent.putExtra("CHECK_IN", checkIn)
            intent.putExtra("CHECK_OUT", checkOut)
            intent.putExtra("GUESTS", guests)
            intent.putExtra("ROOMS", rooms)
            startActivity(intent)
        }
    }

    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, day ->
                val formatted = "%02d/%02d/%04d".format(day, month + 1, year)
                onDateSelected(formatted)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

}