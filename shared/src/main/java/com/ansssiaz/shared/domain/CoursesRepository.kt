package com.ansssiaz.shared.domain

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
}