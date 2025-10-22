package com.ansssiaz.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.feature.main.usecase.GetCoursesUseCase
import com.ansssiaz.feature.main.usecase.GetFavouriteIdsUseCase
import com.ansssiaz.feature.main.usecase.SortCoursesUseCase
import com.ansssiaz.feature.main.usecase.ToggleFavouriteUseCase
import com.ansssiaz.shared.UiStateStatus
import com.ansssiaz.shared.domain.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val sortCoursesUseCase: SortCoursesUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val getFavouriteIdsUseCase: GetFavouriteIdsUseCase,
) : ViewModel() {
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
                val courses = getCoursesUseCase()

                val favouriteIds = getFavouriteIdsUseCase()

                val updatedCourses = courses.map { course ->
                    course.copy(hasLike = favouriteIds.contains(course.id))
                }

                _state.update {
                    it.copy(
                        courses = updatedCourses,
                        sortedCourses = updatedCourses,
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
        val sortedCourses = sortCoursesUseCase(
            state.value.courses,
            state.value.isSorted
        )

        _state.update {
            it.copy(
                sortedCourses = sortedCourses,
                isSorted = !state.value.isSorted
            )
        }
    }

    fun syncWithFavourites() {
        viewModelScope.launch {
            val favouriteIds = getFavouriteIdsUseCase()
            updateCoursesState(
                shouldUpdate = { true },
                transform = { course ->
                    course.copy(hasLike = favouriteIds.contains(course.id))
                }
            )
        }
    }

    fun addToFavourites(course: Course) {
        viewModelScope.launch {
            try {
                toggleFavouriteUseCase(course)

                updateCoursesState(
                    shouldUpdate = { it.id == course.id },
                    transform = { it.copy(hasLike = !course.hasLike) }
                )

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        uiStateStatus = UiStateStatus.Error(e)
                    )
                }
            }
        }
    }

    private fun updateCoursesState(
        shouldUpdate: (Course) -> Boolean,
        transform: (Course) -> Course
    ) {
        _state.update { currentState ->
            currentState.copy(
                courses = currentState.courses.map { course ->
                    if (shouldUpdate(course)) {
                        transform(course)
                    } else {
                        course
                    }
                },
                sortedCourses = currentState.sortedCourses.map { course ->
                    if (shouldUpdate(course)) {
                        transform(course)
                    } else {
                        course
                    }
                }
            )
        }
    }
}