package com.ansssiaz.itcourses.util
fun getImageResId(index: Int): Int = when (index % 3) {
    0 -> R.drawable.course_image_1
    1 -> R.drawable.course_image_2
    else -> R.drawable.course_image_3
}