package com.alicimsamil.flightapp.common

import java.lang.Exception

sealed class Result<T> {
    data class Success<T>(val data : T) : Result<T>()
    data class Error(val exception: Exception?) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
