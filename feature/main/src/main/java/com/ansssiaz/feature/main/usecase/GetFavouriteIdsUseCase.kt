package com.ansssiaz.feature.main.usecase

import com.ansssiaz.shared.domain.FavouritesRepository
import jakarta.inject.Inject

class GetFavouriteIdsUseCase @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(): List<Int> {
        return favouritesRepository.getFavouriteIds()
    }
}