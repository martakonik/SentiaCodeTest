package com.martakonik.sentiacodetest.ui.propertydetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<String>()
    val state: LiveData<String> get() = _state

    fun loadDetails(propertyId: String) {
        viewModelScope.launch {
            _state.value = propertyId
        }
    }
}