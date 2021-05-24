package com.martakonik.sentiacodetest.api

import com.martakonik.sentiacodetest.data.GetPropertyResponse
import retrofit2.http.GET

interface PropertiesEndpoints {

    // https://f213b61d-6411-4018-a178-53863ed9f8ec.mock.pstmn.io/properties

    @GET("properties")
    suspend fun getProperties(): GetPropertyResponse
}