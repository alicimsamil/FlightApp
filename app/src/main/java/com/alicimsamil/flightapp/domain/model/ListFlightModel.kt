package com.alicimsamil.flightapp.domain.model

import com.alicimsamil.flightapp.common.Status

data class ListFlightModel(
    var airlineName: String?=null,
    var departureIata: String?=null,
    var departureEstimated: String?=null,
    var arrivalIata: String?=null,
    var arrivalEstimated: String?=null,
    var delay:String?=null,
    var status: Status?=null,
    var flightNumber : Int?=null,
    var airlineIata : String?=null
)
