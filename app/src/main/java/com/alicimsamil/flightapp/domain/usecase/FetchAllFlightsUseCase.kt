package com.alicimsamil.flightapp.domain.usecase

import com.alicimsamil.flightapp.common.Result
import com.alicimsamil.flightapp.data.repository.FlightRepository
import com.alicimsamil.flightapp.util.Constants
import com.alicimsamil.flightapp.util.extensions.toListFlightModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAllFlightsUseCase @Inject constructor(private val repository: FlightRepository) {
    suspend operator fun invoke() = flow {

        emit(Result.Loading)

        repository.fetchAllFlights(Constants.API_KEY, Constants.LIMIT)?.let {

            emit(Result.Success(it.toListFlightModel()))

        } ?: run {

            emit(Result.Error(null))

        }

    }


}