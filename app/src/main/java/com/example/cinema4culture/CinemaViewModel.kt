package com.example.cinema4culture


import androidx.lifecycle.ViewModel
import com.example.cinema4culture.data.CinemaAppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CinemaViewModel: ViewModel() {


    private val _uiState = MutableStateFlow(
        CinemaAppUiState(currentScreen = initializeStartScreen())
    )

    val uiState: StateFlow<CinemaAppUiState> = _uiState.asStateFlow()


    private fun initializeStartScreen(): Int {
        return R.string.cinema4culture
    }
}