package com.martakonik.sentiacodetest.api

import com.martakonik.sentiacodetest.data.GetPropertyResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class ApiServiceTest {

    @MockK
    val service: PropertiesEndpoints = mockk()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    private val tested = ApiService(testDispatcher, service)

    @ExperimentalCoroutinesApi
    @Test
    fun `should call getProperties on service when getProperties called`() {
        coEvery { service.getProperties() } returns GetPropertyResponse(emptyList())

        verify { service wasNot Called }
        testScope.runBlockingTest {
            tested.getProperties()
        }
        coVerify { service.getProperties() }
    }
}

