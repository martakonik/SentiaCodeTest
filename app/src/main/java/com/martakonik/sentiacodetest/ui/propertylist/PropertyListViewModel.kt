package com.martakonik.sentiacodetest.ui.propertylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martakonik.sentiacodetest.R
import com.martakonik.sentiacodetest.repository.PropertiesRepository
import com.martakonik.sentiacodetest.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository
) : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = _state

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            runCatching { propertiesRepository.getProperties() }
                .onFailure { _state.value = UiState.ErrorReceived(R.string.error_message)}
                .onSuccess { _state.value = UiState.DataReceived(it) }
        }
    }
}