package com.martakonik.sentiacodetest

import com.martakonik.sentiacodetest.api.PropertiesEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PropertiesEndpointsModule {

    @Provides
    fun providePropertiesService(retrofit: Retrofit): PropertiesEndpoints {
        return retrofit.create(PropertiesEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val BASE_URL = "https://f213b61d-6411-4018-a178-53863ed9f8ec.mock.pstmn.io/"
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}