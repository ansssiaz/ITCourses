package com.ansssiaz.feature.main.usecase

import com.ansssiaz.shared.domain.Course
import com.ansssiaz.shared.domain.CoursesRepository
import jakarta.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke(): List<Course> {
        return coursesRepository.getCourses()
    }
}