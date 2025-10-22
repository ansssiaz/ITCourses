package com.feature.favourites.usecase

import com.ansssiaz.shared.domain.FavouritesRepository
import jakarta.inject.Inject

class DeleteFromFavouritesUseCase @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(courseId: Int) {
        favouritesRepository.deleteFromFavourites(courseId)
    }
}