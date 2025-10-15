package com.ansssiaz.feature.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(LogInUiState())
    val state = _state.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<LogInNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEmailChange(email: String) {
        val maskedEmail = applyEmailMask(email)
        _state.update {
            it.copy(
                email = maskedEmail,
                isEmailValid = isValidEmail(maskedEmail),
                isLogInButtonEnabled = isValidEmail(maskedEmail) && it.password.isNotBlank()
            )
        }
    }

    private fun applyEmailMask(input: String): String {
        if (input.startsWith("@")) return ""

        val asciiOnly = input.filter { char ->
            char.code in 0..127
        }

        val atIndex = asciiOnly.indexOf('@')

        if (atIndex != -1) {
            val localPart = asciiOnly.substring(0, atIndex)
            val domainPart = asciiOnly.substring(atIndex + 1)

            val filteredLocal = localPart.filter { it.isLetterOrDigit() || it in "._-" }
            val filteredDomain = domainPart.filter { it.isLetterOrDigit() || it in "." }

            val finalDomain = filteredDomain.replace("..", ".")

            return if (filteredLocal.isNotEmpty()) {
                "$filteredLocal@$finalDomain"
            } else {
                ""
            }
        }
        return asciiOnly.filter { it.isLetterOrDigit() || it in "._-" }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(emailRegex) && email.isNotBlank()
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(
                password = password,
                isLogInButtonEnabled = it.isEmailValid && password.isNotBlank()
            )
        }
    }

    fun onIsPasswordVisibleChange(isPasswordVisible: Boolean) {
        _state.update {
            it.copy(isPasswordVisible = isPasswordVisible)
        }
    }

    fun onLogInButtonClick() {
        if (state.value.isLogInButtonEnabled) {
            viewModelScope.launch {
                _navigationEvent.emit(LogInNavigationEvent.NavigateToMainScreen)
            }
        }
    }

    sealed class LogInNavigationEvent {
        object NavigateToMainScreen : LogInNavigationEvent()
    }
}