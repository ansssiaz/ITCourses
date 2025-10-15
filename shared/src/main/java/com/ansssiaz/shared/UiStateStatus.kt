package com.ansssiaz.shared

sealed interface UiStateStatus {
    data object Success : UiStateStatus
    data class Error(val throwable: Throwable) : UiStateStatus
    data object Loading : UiStateStatus
    val throwableOrNull: Throwable?
        get() = (this as? Error)?.throwable
}