package com.feature.favourites

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ansssiaz.component.theme.Green
import com.ansssiaz.component.theme.White
import com.ansssiaz.itcourses.util.getErrorText
import com.ansssiaz.shared.presentation.CourseItem
import com.feature.favourites.viewmodel.FavouritesViewModel

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val favouritesUiState by viewModel.state.collectAsStateWithLifecycle()
    val status = favouritesUiState.uiStateStatus
    val context = LocalContext.current

    LaunchedEffect(status.throwableOrNull) {
        status.throwableOrNull?.let { error ->
            val errorText = error.getErrorText(context)
            Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getFavourites()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when {
            favouritesUiState.isLoading -> {
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
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = stringResource(R.string.favourites_title),
                            color = White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }

                    items(favouritesUiState.courses) { course ->
                        CourseItem(
                            course = course,
                            onAddToFavouritesClick = { viewModel.deleteFromFavourites(course) }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
        }
    }
}