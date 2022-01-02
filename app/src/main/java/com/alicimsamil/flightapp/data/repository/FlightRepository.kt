package com.alicimsamil.flightapp.data.repository

import com.alicimsamil.flightapp.data.remote.RemoteDataSourceProvider
import javax.inject.Inject

class FlightRepository @Inject constructor(private val remoteDataSourceProvider: RemoteDataSourceProvider) {

    suspend fun fetchAllFlights(apiKey: String, limit: Int) =
        remoteDataSourceProvider.fetchAllFlights(apiKey, limit)

    suspend fun fetchFlight(
        apiKey: String,
        flightNumber: Int,
        airlineIata: String,
        limit: Int
    ) = remoteDataSourceProvider.fetchFlight(apiKey, flightNumber, airlineIata,limit)


}