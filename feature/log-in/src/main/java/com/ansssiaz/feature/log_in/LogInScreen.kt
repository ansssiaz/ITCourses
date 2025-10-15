package com.ansssiaz.feature.log_in

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ansssiaz.component.theme.White
import com.ansssiaz.feature.log_in.viewmodel.LogInViewModel
import com.example.ui_components.Destination.Main
import com.example.ui_components.Destination.LogIn
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LogInScreen(
    navController: NavController,
    viewModel: LogInViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(true) {
            viewModel.navigationEvent.collectLatest { event ->
                when (event) {
                    LogInViewModel.LogInNavigationEvent.NavigateToMainScreen -> {
                        navController.navigate(Main) {
                            popUpTo(LogIn) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogInTitle()
            LogInForm(
                email = state.email,
                password = state.password,
                isEmailValid = state.isEmailValid,
                isPasswordVisible = state.isPasswordVisible,
                isLogInButtonEnabled = state.isLogInButtonEnabled,
                onLogInClick = viewModel::onLogInButtonClick,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onPasswordVisibilityChange = viewModel::onIsPasswordVisibleChange
            )
            LogInFooter()
        }
    }
}

@Composable
private fun LogInTitle() {
    Text(
        text = stringResource(R.string.log_in_title),
        color = White,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
    )
}