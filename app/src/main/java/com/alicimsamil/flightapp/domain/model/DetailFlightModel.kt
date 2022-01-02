package com.alicimsamil.flightapp.domain.model

data class DetailFlightModel(
    var flightCode:String?=null,
    var departureAirportAbb:String?=null,
    var departureAirportName:String?=null,
    var departureDate:String?=null,
    var arrivalAirportAbb:String?=null,
    var arrivalAirportName:String?=null,
    var arrivalDate:String?=null,
    var gateName:String?=null,
    var terminal:String?=null,
    var delay:String?=null
)
