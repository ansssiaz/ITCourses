package com.example.ui_components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ansssiaz.component.theme.ExtraLightGray
import com.ansssiaz.component.theme.Green
import com.ansssiaz.component.theme.White

@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
) {
    Button(
        onClick = { onClick() },
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            disabledContainerColor = Green.copy(alpha = 0.4f)
        )
    )
    {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (enabled) {
                White
            } else {
                ExtraLightGray
            },
            textAlign = TextAlign.Center,
        )
    }
}