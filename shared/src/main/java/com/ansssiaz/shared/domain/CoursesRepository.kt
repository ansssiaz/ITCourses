package com.ansssiaz.shared.domain

import com.ansssiaz.shared.data.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
}