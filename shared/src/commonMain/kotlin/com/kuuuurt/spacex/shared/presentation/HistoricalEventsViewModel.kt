package com.kuuuurt.spacex.shared.presentation

import com.kuuuurt.spacex.shared.SharedServiceLocator
import com.kuuuurt.spacex.shared.domain.entities.HistoricalEvent
import com.kuuuurt.spacex.shared.domain.usecases.GetHistoricalEvents
import com.kuuuurt.spacex.shared.presentation.utils.CommonViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class HistoricalEventsViewModel(
    private val getHistoricalEvents: GetHistoricalEvents
) : CommonViewModel() {
    private val _historicalEvents = MutableStateFlow<List<HistoricalEvent>?>(null)
    val historicalEvents: Flow<List<HistoricalEvent>> get() = _historicalEvents.filterNotNull()

    init {
        viewModelScope.launch {
            _historicalEvents.value = getHistoricalEvents()
        }
    }

    companion object {
        fun create() = HistoricalEventsViewModel(SharedServiceLocator.getHistoricalEvents)
    }
}