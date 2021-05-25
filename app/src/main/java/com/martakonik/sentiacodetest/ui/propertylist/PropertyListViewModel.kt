package com.martakonik.sentiacodetest.ui.propertylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martakonik.sentiacodetest.data.Property
import com.martakonik.sentiacodetest.repository.PropertiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository
) : ViewModel() {

    private val _state = MutableLiveData<List<Property>>()
    val state: LiveData<List<Property>> get() = _state

    init {
        viewModelScope.launch {
            //loading
            runCatching { propertiesRepository.getProperties() }
                .onFailure { }
                .onSuccess { _state.value = it }
        }
    }
}