package com.alicimsamil.flightapp.data.model.response

data class Departure(
    val actual: Any?=null,
    val actual_runway: Any?=null,
    val airport: String?=null,
    val delay: Any?=null,
    val estimated: String?=null,
    val estimated_runway: Any?=null,
    val gate: String?=null,
    val iata: String?=null,
    val icao: String?=null,
    val scheduled: String?=null,
    val terminal: Any?=null,
    val timezone: String?=null
)