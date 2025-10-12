package com.example.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ansssiaz.component.theme.Blue
import com.ansssiaz.component.theme.LightOrange
import com.ansssiaz.component.theme.Orange

@Composable
fun GroupSocialButtons(
    dividerColor: Color,
    onVkClick: () -> Unit,
    onOkClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = dividerColor
        )

        Spacer(modifier = Modifier.size(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialButton(
                icon = R.drawable.ic_vk,
                color = Blue,
                onClick = onVkClick,
                modifier = Modifier.weight(1f),
            )

            GradientSocialButton(
                icon = R.drawable.ic_ok,
                gradientColors = listOf(LightOrange, Orange),
                onClick = onOkClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun SocialButton(
    color: Color,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(30.dp),
        modifier = modifier,
        contentPadding = PaddingValues.Zero
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            alignment = Alignment.Center
        )
    }
}

@Composable
fun GradientSocialButton(
    icon: Int,
    gradientColors: List<Color>,
    onClick: () -> Unit,
    modifier: Modifier
) {
    val brush = remember(gradientColors) {
        Brush.verticalGradient(
            colors = gradientColors,
            startY = 0f,
            endY = Float.POSITIVE_INFINITY
        )
    }

    Box(
        modifier = modifier
            .background(
                brush = brush,
                shape = RoundedCornerShape(30.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
        )
    }
}