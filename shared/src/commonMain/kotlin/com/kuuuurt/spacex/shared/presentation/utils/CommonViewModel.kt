package com.kuuuurt.spacex.shared.presentation.utils

import kotlinx.coroutines.CoroutineScope

expect open class CommonViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared()
}