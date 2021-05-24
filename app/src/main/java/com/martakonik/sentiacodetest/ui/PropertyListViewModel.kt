package com.martakonik.sentiacodetest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martakonik.sentiacodetest.data.Property
import com.martakonik.sentiacodetest.repository.PropertiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository
) : ViewModel() {


    private val _state = MutableLiveData<List<Property>>()
    val state: LiveData<List<Property>> get() = _state

    fun getPropertyList() {
        viewModelScope.launch {
            propertiesRepository.getProperties()
                .onStart { /* _foo.value = loading state */ }
                .catch { exception ->
                    /* _foo.value = error state */
                    Log.e("m", "", exception)
                }
                .collect { list: List<Property> ->
                    _state.value = list
                }
        }
    }

}