package com.example.ui_components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ansssiaz.component.theme.ExtraLightGray
import com.ansssiaz.component.theme.LightGray

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(
        color = ExtraLightGray,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    label: @Composable (() -> Unit)? = null,
    placeholderText: String = stringResource(R.string.placeholder_default),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(30.dp),
    colors: TextFieldColors = TextFieldDefaults.colors().copy(
        unfocusedContainerColor = LightGray,
        focusedContainerColor = LightGray,
        disabledContainerColor = LightGray.copy(alpha = 0.5f),
        unfocusedTextColor = ExtraLightGray,
        focusedTextColor = ExtraLightGray,
        cursorColor = ExtraLightGray,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedPlaceholderColor = ExtraLightGray,
        focusedPlaceholderColor = ExtraLightGray,
        errorContainerColor = LightGray,
        errorIndicatorColor = Color.Transparent,
        errorTextColor = Color.Red,
        errorCursorColor = ExtraLightGray
    )
) {
    Column {
        label?.invoke()

        Spacer(modifier = Modifier.size(4.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = null,
            placeholder = { Text(text = placeholderText) },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource ?: remember { MutableInteractionSource() },
            shape = shape,
            colors = colors
        )

        if (isError) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.email_error),
                color = Color.Red,
                fontSize = 12.sp
            )
        } else {
            supportingText?.let {
                Spacer(modifier = Modifier.height(4.dp))
                it()
            }
        }
    }
}