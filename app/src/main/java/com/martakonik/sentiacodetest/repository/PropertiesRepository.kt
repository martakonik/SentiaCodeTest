package com.martakonik.sentiacodetest.repository

import com.martakonik.sentiacodetest.api.ApiService
import com.martakonik.sentiacodetest.data.Property
import javax.inject.Inject

class PropertiesRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProperties(): List<Property> {
        val response = apiService.getProperties()
        return response.data
    }
}