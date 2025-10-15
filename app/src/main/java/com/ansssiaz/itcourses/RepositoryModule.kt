package com.ansssiaz.itcourses

import com.ansssiaz.shared.data.CoursesRepositoryImpl
import com.ansssiaz.shared.domain.CoursesRepository
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