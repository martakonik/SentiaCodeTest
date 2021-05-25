package com.martakonik.sentiacodetest.api

import com.martakonik.sentiacodetest.data.GetPropertyResponse
import retrofit2.http.GET

interface PropertiesEndpoints {

    @GET("properties")
    suspend fun getProperties(): GetPropertyResponse
}