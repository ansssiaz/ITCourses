package com.ansssiaz.itcourses

import android.content.Context
import androidx.room.Room
import com.ansssiaz.shared.data.db.FavouriteCoursesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ITCoursesAppDatabase {
        return Room.databaseBuilder(
            context,
            ITCoursesAppDatabase::class.java,
            "it_courses_database"
        ).build()
    }

    @Provides
    fun provideFavoritesDao(database: ITCoursesAppDatabase): FavouriteCoursesDao {
        return database.favouriteCoursesDao()
    }
}