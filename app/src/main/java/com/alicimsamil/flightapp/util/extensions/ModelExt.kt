package com.alicimsamil.flightapp.util.extensions

import com.alicimsamil.flightapp.data.model.response.FlightResponse
import com.alicimsamil.flightapp.domain.model.ListFlightModel
import com.alicimsamil.flightapp.common.Status
import com.alicimsamil.flightapp.domain.model.DetailFlightModel

fun FlightResponse.toListFlightModel() : ArrayList<ListFlightModel>{
    val list= arrayListOf<ListFlightModel>()

    this.data?.forEach {
        list.add(ListFlightModel(
            it.airline?.name,
            it.departure?.iata,
            it.departure?.estimated,
            it.arrival?.iata,
            it.arrival?.estimated,
            it.departure?.delay.toString(),
            when (it.flight_status) {
                "cancelled" -> Status.cancelled
                "scheduled" -> Status.scheduled
                "active" -> Status.active
                else -> Status.cancelled
            },
            it.flight?.number?.toInt(),
            it.airline?.iata
        ))
    }

    return list

}

fun FlightResponse.toDetailFlightModel() : DetailFlightModel{

    return DetailFlightModel(
        this.data?.get(0)?.flight?.number,
        this.data?.get(0)?.departure?.iata,
        this.data?.get(0)?.departure?.airport,
        this.data?.get(0)?.departure?.estimated,
        this.data?.get(0)?.arrival?.iata,
        this.data?.get(0)?.arrival?.airport,
        this.data?.get(0)?.arrival?.estimated,
        this.data?.get(0)?.departure?.gate,
        this.data?.get(0)?.departure?.terminal?.toString(),
        this.data?.get(0)?.departure?.delay?.toString()
    )

}