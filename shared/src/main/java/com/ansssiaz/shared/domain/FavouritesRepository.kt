package com.ansssiaz.shared.domain

interface FavouritesRepository {
    suspend fun addToFavourites(course: Course)
    suspend fun getFavourites(): List<Course>

    suspend fun getFavouriteIds(): List<Int>

    suspend fun deleteFromFavourites(id: Int)
}