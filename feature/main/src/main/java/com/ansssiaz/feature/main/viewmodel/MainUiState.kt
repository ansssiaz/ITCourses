package com.ansssiaz.feature.main.viewmodel

import com.ansssiaz.shared.UiStateStatus
import com.ansssiaz.shared.domain.Course

data class MainUiState(
    val courses: List<Course> = emptyList(),
    val sortedCourses: List<Course> = emptyList(),
    val isSorted: Boolean = false,
    val uiStateStatus: UiStateStatus = UiStateStatus.Success
){
    val isLoading: Boolean = uiStateStatus == UiStateStatus.Loading && courses.isEmpty()
}