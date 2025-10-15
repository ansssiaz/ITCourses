package com.ansssiaz.itcourses

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ansssiaz.shared.data.db.FavouriteCourse
import com.ansssiaz.shared.data.db.FavouriteCoursesDao

@Database(
    entities = [FavouriteCourse::class],
    version = 1,
    exportSchema = false
)
abstract class ITCoursesAppDatabase : RoomDatabase() {
    abstract fun favouriteCoursesDao(): FavouriteCoursesDao
}