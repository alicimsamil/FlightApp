package com.alicimsamil.flightapp.data.model.response

data class Pagination(
    val count: Int?=null,
    val limit: Int?=null,
    val offset: Int?=null,
    val total: Int?=null
)