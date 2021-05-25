package com.martakonik.sentiacodetest.utils

import com.martakonik.sentiacodetest.data.Property

sealed class UiState {
    class DataReceived(val propertyList: List<Property>): UiState()
    object Loading: UiState()
    class ErrorReceived(val errorMessage: Int): UiState()
}