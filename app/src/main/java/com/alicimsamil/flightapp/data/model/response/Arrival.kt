package com.alicimsamil.flightapp.data.model.response

data class Arrival(
    val actual: Any?=null,
    val actual_runway: Any?=null,
    val airport: String?=null,
    val baggage: Any?=null,
    val delay: Any?=null,
    val estimated: String?=null,
    val estimated_runway: Any?=null,
    val gate: Any?=null,
    val iata: String?=null,
    val icao: String?=null,
    val scheduled: String?=null,
    val terminal: Any?=null,
    val timezone: String?=null
)