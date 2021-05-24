package com.martakonik.sentiacodetest.data

data class Location (
    val address: String,
    val state: String,
    val suburb: String,
    val postcode: String? = null,
    val latitude: Double,
    val longitude: Double
)