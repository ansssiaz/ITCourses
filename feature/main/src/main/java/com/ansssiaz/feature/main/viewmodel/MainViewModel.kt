package com.ansssiaz.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.shared.UiStateStatus
import com.ansssiaz.shared.domain.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CoursesRepository) : ViewModel() {
    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    init {
        getCourses()
    }

    private fun getCourses() {
        _state.update {
            it.copy(
                uiStateStatus = UiStateStatus.Loading
            )
        }
        viewModelScope.launch {
            try {
                val courses = repository.getCourses()
                _state.update {
                    it.copy(
                        courses = courses,
                        sortedCourses = courses,
                        uiStateStatus = UiStateStatus.Success
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        uiStateStatus = UiStateStatus.Error(e)
                    )
                }
            }
        }
    }

    fun sortCourses() {
        val sortedCourses = if (state.value.isSorted) {
            state.value.courses
        } else {
            state.value.courses.sortedByDescending { it.publishDate }
        }
        _state.update {
            it.copy(
                sortedCourses = sortedCourses,
                isSorted = !state.value.isSorted
            )
        }
    }
}