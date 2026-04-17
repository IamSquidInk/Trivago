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
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        val etDateRange = findViewById<EditText>(R.id.etDateRange) // 👈 replaced etCheckIn/etCheckOut
        val etGuests = findViewById<EditText>(R.id.etGuests)
        val etRooms = findViewById<EditText>(R.id.etRooms)
        val tvResultsHeader = findViewById<TextView>(R.id.tvResultsHeader)
        val rvHotels = findViewById<RecyclerView>(R.id.rvHotels)

        // Date range picker 👇
        etDateRange.setOnClickListener {
            val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select Dates")
                .build()

            dateRangePicker.addOnPositiveButtonClickListener { selection ->
                val startDate = selection.first
                val endDate = selection.second
                val sdf = SimpleDateFormat("MMM dd", Locale.getDefault())
                val formattedStart = sdf.format(Date(startDate))
                val formattedEnd = sdf.format(Date(endDate))
                etDateRange.setText("$formattedStart - $formattedEnd")
            }

            dateRangePicker.show(supportFragmentManager, "DATE_RANGE_PICKER")
        }

        rvHotels.layoutManager = LinearLayoutManager(this)
        loadHotels(rvHotels, etDateRange, etGuests, etRooms)

        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            val destination = etDestination.text.toString().trim()
            val dateRange = etDateRange.text.toString().trim()
            val guests = etGuests.text.toString().trim()
            val rooms = etRooms.text.toString().trim()

            if (destination.isEmpty()) {
                Toast.makeText(this, "Please enter a destination", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dateRange.isEmpty()) {
                Toast.makeText(this, "Please select your dates", Toast.LENGTH_SHORT).show()
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
            loadHotels(rvHotels, etDateRange, etGuests, etRooms)
        }
    }

    private fun loadHotels(
        rvHotels: RecyclerView,
        etDateRange: EditText,
        etGuests: EditText,
        etRooms: EditText
    ) {
        val dateRange = etDateRange.text.toString().trim() // 👈 read it here

        rvHotels.adapter = HotelAdapter(currentHotels, dateRange) { hotel -> // 👈 pass it here
            val guests = etGuests.text.toString().trim()
            val rooms = etRooms.text.toString().trim()

            val intent = Intent(this, HotelDetailsActivity::class.java)
            intent.putExtra("HOTEL_NAME", hotel.name)
            intent.putExtra("HOTEL_LOCATION", hotel.location)
            intent.putExtra("HOTEL_PRICE", hotel.price)
            intent.putExtra("HOTEL_DESCRIPTION", hotel.description)
            intent.putExtra("HOTEL_RATING", hotel.rating)
            intent.putExtra("HOTEL_IMAGE", hotel.imageRes)
            intent.putExtra("DATE_RANGE", dateRange)
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