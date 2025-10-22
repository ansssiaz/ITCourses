package com.ansssiaz.feature.main.usecase

import com.ansssiaz.shared.domain.Course
import jakarta.inject.Inject

class SortCoursesUseCase @Inject constructor() {
    operator fun invoke(
        courses: List<Course>,
        isSorted: Boolean
    ): List<Course> {
        return if (isSorted) {
            courses
        } else {
            courses.sortedByDescending { it.publishDate }
        }
    }
}