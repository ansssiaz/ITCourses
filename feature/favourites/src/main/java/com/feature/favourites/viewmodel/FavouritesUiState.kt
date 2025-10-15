package com.feature.favourites.viewmodel

import com.ansssiaz.shared.UiStateStatus
import com.ansssiaz.shared.domain.Course

data class FavouritesUiState(
    val courses: List<Course> = emptyList(),
    val uiStateStatus: UiStateStatus = UiStateStatus.Success
){
    val isLoading: Boolean = uiStateStatus == UiStateStatus.Loading && courses.isEmpty()
}
