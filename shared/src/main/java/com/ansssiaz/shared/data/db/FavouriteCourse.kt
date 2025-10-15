package com.ansssiaz.shared.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ansssiaz.itcourses.util.formatDate
import com.ansssiaz.shared.domain.Course

@Entity(tableName = "favourite_courses")
data class FavouriteCourse(
    @PrimaryKey
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
    val imageId: Int
)

fun FavouriteCourse.toCourse(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = true,
    publishDate = publishDate.formatDate(),
    imageId = imageId
)

fun Course.toFavouriteCourse(): FavouriteCourse = FavouriteCourse(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate,
    imageId = imageId,
)