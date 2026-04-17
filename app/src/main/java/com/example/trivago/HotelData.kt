package com.example.trivago

object HotelData {
    val hotels = mapOf(
        "cebu" to listOf(
            Hotel(
                name = "Grand Cebu Hotel",
                location = "Cebu City",
                price = "$80/night",
                description = "A luxurious hotel in the heart of Cebu City, offering stunning views and world-class amenities.",
                rating = 4.5f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Waterfront Hotel",
                location = "Lahug, Cebu",
                price = "$120/night",
                description = "Experience elegance and comfort at the iconic Waterfront Hotel, Cebu's premier destination.",
                rating = 4.7f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Radisson Blu",
                location = "Cebu Business Park",
                price = "$150/night",
                description = "Modern sophistication meets Filipino hospitality at the Radisson Blu Cebu.",
                rating = 4.8f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "City Suites",
                location = "Fuente, Cebu",
                price = "$60/night",
                description = "Affordable comfort in a prime location, perfect for business and leisure travelers.",
                rating = 4.0f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "The Henry Hotel",
                location = "Banilad, Cebu",
                price = "$100/night",
                description = "A boutique hotel with a unique artistic vibe, offering a one-of-a-kind stay in Cebu.",
                rating = 4.6f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Marco Polo Plaza Hotel",
                location = "Nivel Hills, Cebu",
                price = "$80/night",
                description = "A premier 5-star hotel located on Nivel Hills in Cebu City, offering panoramic views of the city, mountains, and sea",
                rating = 4.5f,
                imageRes = R.drawable.placeholder_hotel
            )
        ),
        "manila" to listOf(
            Hotel(
                name = "Manila Hotel",
                location = "Ermita, Manila",
                price = "$100/night",
                description = "A historic landmark hotel offering timeless elegance in the heart of Manila.",
                rating = 4.5f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Shangri-La",
                location = "Makati, Manila",
                price = "$200/night",
                description = "World-class luxury in the bustling business district of Makati.",
                rating = 4.9f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Holiday Inn",
                location = "Malate, Manila",
                price = "$90/night",
                description = "Comfortable and convenient stay in the vibrant Malate district.",
                rating = 4.2f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Okada Manila",
                location = "Parañaque, Manila",
                price = "$250/night",
                description = "An integrated resort offering unparalleled luxury and entertainment.",
                rating = 4.8f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Sofitel Manila",
                location = "Pasay, Manila",
                price = "$180/night",
                description = "French elegance meets Filipino warmth at the stunning Sofitel Manila.",
                rating = 4.7f,
                imageRes = R.drawable.placeholder_hotel
            )
        ),
        "boracay" to listOf(
            Hotel(
                name = "Henann Resort",
                location = "White Beach, Boracay",
                price = "$150/night",
                description = "Beachfront paradise on the world-famous White Beach of Boracay.",
                rating = 4.6f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Crimson Resort",
                location = "Boracay Island",
                price = "$200/night",
                description = "Luxury clifftop resort with breathtaking views of the Sibuyan Sea.",
                rating = 4.7f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Discovery Shores",
                location = "Station 1, Boracay",
                price = "$300/night",
                description = "The pinnacle of Boracay luxury, located on the finest stretch of White Beach.",
                rating = 4.9f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Astoria Boracay",
                location = "Station 2, Boracay",
                price = "$120/night",
                description = "Stylish and affordable beachside resort in the heart of Boracay.",
                rating = 4.3f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Movenpick Resort",
                location = "Boracay Island",
                price = "$180/night",
                description = "Swiss hospitality on a tropical island, offering a perfect blend of comfort and nature.",
                rating = 4.5f,
                imageRes = R.drawable.placeholder_hotel
            )
        ),
        "davao" to listOf(
            Hotel(
                name = "Marco Polo Davao",
                location = "Davao City",
                price = "$90/night",
                description = "Davao's premier business hotel offering exceptional service and facilities.",
                rating = 4.6f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Seda Abreeza",
                location = "Davao City",
                price = "$110/night",
                description = "Contemporary urban hotel connected to Abreeza Mall in the heart of Davao.",
                rating = 4.5f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Crown Regency",
                location = "Davao City",
                price = "$80/night",
                description = "Comfortable and well-located hotel perfect for exploring Davao City.",
                rating = 4.1f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Microtel Davao",
                location = "Davao City",
                price = "$60/night",
                description = "Budget-friendly hotel with modern amenities in a convenient location.",
                rating = 4.0f,
                imageRes = R.drawable.placeholder_hotel
            ),
            Hotel(
                name = "Park Inn Davao",
                location = "Davao City",
                price = "$75/night",
                description = "A vibrant and modern hotel offering great value in Davao City.",
                rating = 4.2f,
                imageRes = R.drawable.placeholder_hotel
            )
        )
    )
}