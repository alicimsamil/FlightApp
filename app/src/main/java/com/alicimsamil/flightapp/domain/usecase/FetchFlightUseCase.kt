package com.alicimsamil.flightapp.domain.usecase

import com.alicimsamil.flightapp.common.Result
import com.alicimsamil.flightapp.data.repository.FlightRepository
import com.alicimsamil.flightapp.util.extensions.toDetailFlightModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchFlightUseCase @Inject constructor(private val repository: FlightRepository) {
    suspend operator fun invoke(
        apiKey: String,
        flightNumber: Int,
        airlineIata: String,
        limit: Int
    ) = flow {

        emit(Result.Loading)

        repository.fetchFlight(apiKey, flightNumber, airlineIata, limit)?.let {

            emit(Result.Success(it.toDetailFlightModel()))

        } ?: run {

            emit(Result.Error(null))

        }
    }
}