package com.ansssiaz.shared.data.network

import com.ansssiaz.itcourses.util.formatDate
import com.ansssiaz.itcourses.util.getImageResId
import com.ansssiaz.shared.domain.Course
import kotlinx.serialization.Serializable

@Serializable
data class CourseModel(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

@Serializable
data class CoursesResponse(
    val courses: List<CourseModel>
)

fun CourseModel.toCourse(index: Int): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate.formatDate(),
    imageId = getImageResId(index)
)