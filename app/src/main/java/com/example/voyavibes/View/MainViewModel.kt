package com.example.voyavibes.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voyavibes.Data.Airport
import com.example.voyavibes.Data.allAirports
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainViewModel: ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow("")
    val isSearching = _isSearching.asStateFlow()

    private val _airports = MutableStateFlow(allAirports)

    val airports = searchText.combine(_airports) { query, airports ->
        if (query.isBlank()) {
            airports
        } else {
            airports.filter { it.doesMatchSearchQuery(query) }
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _airports.value
        )

    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
    }
}