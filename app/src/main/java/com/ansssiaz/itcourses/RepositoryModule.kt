package com.ansssiaz.itcourses

import com.ansssiaz.shared.data.network.CoursesRepositoryImpl
import com.ansssiaz.shared.data.db.FavouritesRepositoryImpl
import com.ansssiaz.shared.domain.CoursesRepository
import com.ansssiaz.shared.domain.FavouritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CoursesRepositoryModule {
    @Binds
    fun bindCoursesRepositoryImpl(impl: CoursesRepositoryImpl): CoursesRepository
}

@Module
@InstallIn(ViewModelComponent::class)
interface FavouritesRepositoryModule {
    @Binds
    fun bindFavouritesRepositoryImpl(impl: FavouritesRepositoryImpl): FavouritesRepository
}