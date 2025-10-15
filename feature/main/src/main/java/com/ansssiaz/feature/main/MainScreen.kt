package com.ansssiaz.feature.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ansssiaz.component.theme.Green
import com.ansssiaz.feature.main.viewmodel.MainViewModel
import com.ansssiaz.itcourses.util.getErrorText

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val mainUiState by viewModel.state.collectAsStateWithLifecycle()
    val status = mainUiState.uiStateStatus
    val context = LocalContext.current

    LaunchedEffect(status.throwableOrNull) {
        status.throwableOrNull?.let { error ->
            val errorText = error.getErrorText(context)
            Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when {
            mainUiState.isLoading -> {
                CircularProgressIndicator(
                    color = Green,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        SearchAndFilterSection()
                    }

                    item {
                        SortSection(
                            isSorted = mainUiState.isSorted,
                            onSortClick = { viewModel.sortCourses() }
                        )
                    }

                    items(mainUiState.sortedCourses) { course ->
                        CourseItem(course = course)
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
        }
    }
}