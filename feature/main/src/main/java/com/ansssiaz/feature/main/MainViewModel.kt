package com.ansssiaz.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.shared.domain.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CoursesRepository) : ViewModel() {
    fun getCourses(){
        viewModelScope.launch {
            repository.getCourses()
        }
    }
}