package com.feature.favourites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.shared.UiStateStatus
import com.ansssiaz.shared.domain.Course
import com.ansssiaz.shared.domain.FavouritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: FavouritesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FavouritesUiState())
    val state = _state.asStateFlow()

    init {
        getFavourites()
    }

    fun getFavourites() {
        _state.update {
            it.copy(
                uiStateStatus = UiStateStatus.Loading
            )
        }

        viewModelScope.launch {
            try {
                val favorites = repository.getFavourites()
                _state.update {
                    it.copy(
                        courses = favorites,
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

    fun deleteFromFavourites(course: Course) {
        viewModelScope.launch {
            try {
                if (course.hasLike) {
                    repository.deleteFromFavourites(course.id)
                }

                _state.update { currentState ->
                    val updatedCourses = currentState.courses.filter {
                        it.id != course.id
                    }

                    currentState.copy(
                        courses = updatedCourses,
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
}