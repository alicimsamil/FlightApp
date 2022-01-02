package com.alicimsamil.flightapp.presentation.flightlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicimsamil.flightapp.common.Result
import com.alicimsamil.flightapp.domain.model.ListFlightModel
import com.alicimsamil.flightapp.domain.usecase.FetchAllFlightsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightListViewModel @Inject constructor(private val fetchAllFlightsUseCase: FetchAllFlightsUseCase) :
    ViewModel() {

    var mutableFlightData = MutableLiveData<ArrayList<ListFlightModel>>()
        private set

    var mutableProgressData = MutableLiveData<Boolean>()
        private set

    var mutableErrorData = MutableLiveData<Boolean>()
        private set

    fun fetchAllFlights() {

        viewModelScope.launch {

            fetchAllFlightsUseCase.invoke().collect {

                when (it) {
                    is Result.Success -> {
                        mutableFlightData.value = it.data
                        mutableProgressData.value = false
                        mutableErrorData.value = false
                    }
                    is Result.Error -> {
                        mutableErrorData.value = true
                        mutableProgressData.value = false
                    }
                    is Result.Loading -> {
                        mutableProgressData.value = true
                        mutableErrorData.value = false
                    }
                }
            }
        }
    }
}