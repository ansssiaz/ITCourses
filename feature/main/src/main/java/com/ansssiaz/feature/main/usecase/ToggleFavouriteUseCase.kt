package com.ansssiaz.feature.main.usecase

import com.ansssiaz.shared.domain.Course
import com.ansssiaz.shared.domain.FavouritesRepository
import jakarta.inject.Inject

class ToggleFavouriteUseCase @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(course: Course) {
        if (course.hasLike) {
            favouritesRepository.deleteFromFavourites(course.id)
        } else {
            favouritesRepository.addToFavourites(course)
        }
    }
}