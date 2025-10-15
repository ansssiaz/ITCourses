package com.ansssiaz.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteCoursesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: FavouriteCourse)

    @Query("DELETE FROM favourite_courses WHERE id = :courseId")
    suspend fun deleteById(courseId: Int)

    @Query("SELECT * FROM favourite_courses")
    suspend fun getFavourites(): List<FavouriteCourse>
}