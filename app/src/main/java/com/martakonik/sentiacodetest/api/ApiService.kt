package com.martakonik.sentiacodetest.api

import com.martakonik.sentiacodetest.data.GetPropertyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(
    private val service: PropertiesEndpoints
) {
    suspend fun getProperties(): GetPropertyResponse = withContext(Dispatchers.IO) {
        service.getProperties()
    }
}