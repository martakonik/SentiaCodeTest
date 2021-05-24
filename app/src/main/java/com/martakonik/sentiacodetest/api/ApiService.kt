package com.martakonik.sentiacodetest.api

import com.martakonik.sentiacodetest.data.GetPropertyResponse
import javax.inject.Inject

class ApiService @Inject constructor(
    private val service: PropertiesEndpoints
) {
    suspend fun getProperties(): GetPropertyResponse = service.getProperties()
}