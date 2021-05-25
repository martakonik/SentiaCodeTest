package com.martakonik.sentiacodetest.api

import com.martakonik.sentiacodetest.data.GetPropertyResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ApiService @Inject constructor(
    @Named("networkContext") private val networkContext: CoroutineDispatcher,
    private val service: PropertiesEndpoints
) {
    suspend fun getProperties(): GetPropertyResponse = withContext(networkContext) {
        service.getProperties()
    }
}