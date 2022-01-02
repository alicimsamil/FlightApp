package com.alicimsamil.flightapp.presentation.flightdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicimsamil.flightapp.common.Result
import com.alicimsamil.flightapp.domain.model.DetailFlightModel
import com.alicimsamil.flightapp.domain.usecase.FetchFlightUseCase
import com.alicimsamil.flightapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightDetailViewModel @Inject constructor(private val fetchFlightUseCase: FetchFlightUseCase) :
    ViewModel() {

    var mutableFlightDetailData = MutableLiveData<DetailFlightModel>()
        private set

    var mutableProgressData = MutableLiveData<Boolean>()
        private set

    var mutableErrorData = MutableLiveData<Boolean>()
        private set


    fun fetchFlightData(
        flightNumber: Int,
        airlineIata: String) {

        viewModelScope.launch {
            fetchFlightUseCase.invoke(Constants.API_KEY,flightNumber,airlineIata,1).collect {

                when(it){

                    is Result.Success -> {
                        mutableFlightDetailData.value=it.data
                        mutableProgressData.value=false
                        mutableErrorData.value=false
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