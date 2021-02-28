package com.kuuuurt.spacex.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kuuuurt.spacex.shared.presentation.HistoricalEventsViewModel
import com.kuuuurt.spacex.shared.presentation.LaunchesViewModel

class MainViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(LaunchesViewModel::class.java) -> {
            LaunchesViewModel.create()
        }
        modelClass.isAssignableFrom(HistoricalEventsViewModel::class.java) -> {
            HistoricalEventsViewModel.create()
        }
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}