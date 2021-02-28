package com.kuuuurt.spacex.shared.presentation

import com.kuuuurt.spacex.shared.SharedServiceLocator
import com.kuuuurt.spacex.shared.domain.entities.Launch
import com.kuuuurt.spacex.shared.domain.usecases.GetLaunches
import com.kuuuurt.spacex.shared.presentation.utils.CommonViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class LaunchesViewModel(private val getLaunches: GetLaunches) : CommonViewModel() {
    private val _launches = MutableStateFlow<List<Launch>?>(null)
    val launches: Flow<List<Launch>> get() = _launches.filterNotNull()

    init {
        viewModelScope.launch {
            _launches.value = getLaunches()
        }
    }

    companion object {
        fun create() = LaunchesViewModel(SharedServiceLocator.getLaunches)
    }
}