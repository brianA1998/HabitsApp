package com.example.habitsapp.home.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set


    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.FrecuencyChange -> TODO()
            DetailEvent.HabitSave -> TODO()
            is DetailEvent.NameChange -> TODO()
            is DetailEvent.ReminderChange -> TODO()
        }
    }
}