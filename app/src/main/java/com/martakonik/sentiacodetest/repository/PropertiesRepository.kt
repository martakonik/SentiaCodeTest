package com.martakonik.sentiacodetest.repository

import com.martakonik.sentiacodetest.api.ApiService
import com.martakonik.sentiacodetest.data.GetPropertyResponse
import com.martakonik.sentiacodetest.data.Property
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PropertiesRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getProperties() : Flow<List<Property>> {
        return flow {
            val response = apiService.getProperties()
            emit(response.data)
        }.flowOn(Dispatchers.IO)
    }
}