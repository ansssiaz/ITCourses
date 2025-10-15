package com.ansssiaz.feature.log_in

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ansssiaz.component.theme.ExtraLightGray
import com.ansssiaz.component.theme.White
import com.example.ui_components.AuthTextField
import com.example.ui_components.PrimaryActionButton

@Composable
fun LogInForm(
    email: String,
    isEmailValid: Boolean,
    isLogInButtonEnabled: Boolean,
    password: String,
    isPasswordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    onLogInClick: () -> Unit
) {
    Spacer(modifier = Modifier.size(28.dp))

    EmailTextField(
        email = email,
        isEmailValid = isEmailValid,
        onEmailChange = onEmailChange
    )

    Spacer(modifier = Modifier.size(16.dp))

    PasswordTextField(
        password = password,
        isPasswordVisible = isPasswordVisible,
        onPasswordChange = onPasswordChange,
        onPasswordVisibilityChange = onPasswordVisibilityChange
    )

    Spacer(modifier = Modifier.size(24.dp))

    LogInButton(
        isEnabled = isLogInButtonEnabled,
        onClick = onLogInClick
    )
}

@Composable
private fun EmailTextField(
    email: String,
    isEmailValid: Boolean,
    onEmailChange: (String) -> Unit
) {
    AuthTextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        label = {
            Text(
                text = stringResource(id = R.string.email),
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        },
        isError = email.isNotBlank() && !isEmailValid,
        placeholderText = stringResource(id = R.string.email_placeholder),
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Unspecified,
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            if (email.isNotBlank()) {
                Icon(
                    painter = if (isEmailValid) {
                        painterResource(id = R.drawable.baseline_check_24)
                    } else {
                        painterResource(
                            id = R.drawable.baseline_close_24
                        )
                    },
                    contentDescription = null,
                    tint = if (isEmailValid) Color.Green else Color.Red
                )
            }
        }
    )
}

@Composable
private fun PasswordTextField(
    password: String,
    isPasswordVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit
) {
    AuthTextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        label = {
            Text(
                text = stringResource(id = R.string.password),
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        },
        placeholderText = stringResource(id = R.string.password_placeholder),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            if (password.isNotBlank()) {
                IconButton(
                    onClick = { onPasswordVisibilityChange(!isPasswordVisible) },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(0.dp)
                ) {
                    Icon(
                        painter = if (isPasswordVisible) {
                            painterResource(id = R.drawable.baseline_visibility_off_24)
                        } else {
                            painterResource(id = R.drawable.baseline_visibility_24)
                        },
                        contentDescription = null,
                        tint = ExtraLightGray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun LogInButton(
    onClick: () -> Unit,
    isEnabled: Boolean
) {
    PrimaryActionButton(
        text = stringResource(id = R.string.log_in),
        onClick = { onClick() },
        enabled = isEnabled
    )
}