package com.ansssiaz.shared.data.db

import com.ansssiaz.shared.domain.Course
import com.ansssiaz.shared.domain.FavouritesRepository
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val dao: FavouriteCoursesDao
) : FavouritesRepository {
    override suspend fun addToFavourites(course: Course) =
        dao.insert(course.toFavouriteCourse())

    override suspend fun getFavourites() =
        dao.getFavourites().map { favouriteCourse ->
            favouriteCourse.toCourse()
        }

    override suspend fun getFavouriteIds(): List<Int> = dao.getFavourites().map { it.id }

    override suspend fun deleteFromFavourites(id: Int) = dao.deleteById(id)
}