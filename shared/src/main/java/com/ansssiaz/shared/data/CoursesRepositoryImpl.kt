package com.ansssiaz.shared.data

import com.ansssiaz.shared.domain.Course
import com.ansssiaz.shared.domain.CoursesRepository
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(private val api: CoursesApi) : CoursesRepository {
    override suspend fun getCourses(): List<Course> =
        api.getCourses().courses.mapIndexed { index, courseModel ->
            courseModel.toCourse(index)
        }
}