package com.ansssiaz.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.shared.UiStateStatus
import com.ansssiaz.shared.domain.Course
import com.ansssiaz.shared.domain.CoursesRepository
import com.ansssiaz.shared.domain.FavouritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coursesRepository: CoursesRepository,
    private val favouritesRepository: FavouritesRepository
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
                val courses = coursesRepository.getCourses()

                val favouriteIds = favouritesRepository.getFavouriteIds()

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

    fun syncWithFavourites() {
        viewModelScope.launch {
            val favouriteIds = favouritesRepository.getFavouriteIds()
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
                if (course.hasLike) {
                    favouritesRepository.deleteFromFavourites(course.id)
                } else {
                    favouritesRepository.addToFavourites(course)
                }

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