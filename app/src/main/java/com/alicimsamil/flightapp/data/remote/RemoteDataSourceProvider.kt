package com.alicimsamil.flightapp.data.remote

import com.alicimsamil.flightapp.data.model.response.FlightResponse
import com.alicimsamil.flightapp.data.network.FlightService
import javax.inject.Inject

class RemoteDataSourceProvider @Inject constructor(private val service: FlightService) : RemoteDataSource {
    override suspend fun fetchAllFlights(apiKey: String, limit: Int): FlightResponse? {
        return service.fetchAllFlights(apiKey,limit)
    }

    override suspend fun fetchFlight(
        apiKey: String,
        flightNumber: Int,
        airlineIata: String,
        limit: Int
    ): FlightResponse? {
        return service.fetchFlight(apiKey, flightNumber, airlineIata,limit)
    }
}