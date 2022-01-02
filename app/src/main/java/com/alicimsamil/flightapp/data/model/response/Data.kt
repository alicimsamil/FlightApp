package com.alicimsamil.flightapp.data.model.response

data class Data(
    val aircraft: Any?=null,
    val airline: Airline?=null,
    val arrival: Arrival?=null,
    val departure: Departure?=null,
    val flight: Flight?=null,
    val flight_date: String?=null,
    val flight_status: String?=null,
    val live: Any?=null
)