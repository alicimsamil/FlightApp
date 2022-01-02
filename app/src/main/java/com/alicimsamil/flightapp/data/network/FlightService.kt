package com.alicimsamil.flightapp.data.network

import com.alicimsamil.flightapp.data.model.response.FlightResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightService {


    @GET("/v1/flights")
    suspend fun fetchAllFlights(@Query("access_key") apiKey: String, @Query("limit") limit: Int) : FlightResponse

    @GET("/v1/flights")
    suspend fun fetchFlight(
        @Query("access_key") apiKey: String,
        @Query("flight_number") flightNumber: Int,
        @Query("airline_iata") airlineIata: String,
        @Query("limit") limit: Int
    ) : FlightResponse


}