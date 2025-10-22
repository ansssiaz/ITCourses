package com.feature.favourites.usecase

import com.ansssiaz.shared.domain.Course
import com.ansssiaz.shared.domain.FavouritesRepository
import jakarta.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(): List<Course> = favouritesRepository.getFavourites()
}