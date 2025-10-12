package com.ansssiaz.feature.log_in

data class LogInUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLogInButtonEnabled: Boolean = false,
    val isEmailValid: Boolean = false
)