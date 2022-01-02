package com.alicimsamil.flightapp.data.remote

import com.alicimsamil.flightapp.data.model.response.FlightResponse

interface RemoteDataSource {


    suspend fun fetchAllFlights(apiKey: String, limit: Int): FlightResponse?

    suspend fun fetchFlight(
        apiKey: String,
        flightNumber: Int,
        airlineIata: String,
        limit : Int
    ): FlightResponse?


}