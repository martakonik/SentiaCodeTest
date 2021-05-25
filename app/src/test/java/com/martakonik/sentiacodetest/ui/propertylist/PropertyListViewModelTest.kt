package com.martakonik.sentiacodetest.ui.propertylist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.martakonik.sentiacodetest.data.Property
import com.martakonik.sentiacodetest.repository.PropertiesRepository
import com.martakonik.sentiacodetest.utils.UiState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule

class PropertyListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    val propertiesRepository: PropertiesRepository = mockk()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: PropertyListViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should call getProperties on VM init`() {
        coEvery { propertiesRepository.getProperties() } returns emptyList()

        verify { propertiesRepository wasNot Called }

        runBlockingTest {
            viewModel = PropertyListViewModel(propertiesRepository)
        }

        coVerify { propertiesRepository.getProperties() }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should emit loading UiState on VM load`() {
        val propertyList = emptyList<Property>()
        coEvery { propertiesRepository.getProperties() } returns propertyList
        runBlockingTest {
            viewModel = PropertyListViewModel(propertiesRepository)
        }

        val list = arrayListOf<UiState>()
        val observer = Observer<UiState> {
            list.add(it)
        }

        viewModel.state.observeForever(observer)

        viewModel.load()

        Assert.assertTrue(list[0] == UiState.Loading)
    }
}