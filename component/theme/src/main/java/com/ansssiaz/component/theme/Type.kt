package com.ansssiaz.component.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val robotoFontFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_semi_bold, FontWeight.SemiBold),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_black, FontWeight.Black)
)

val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = robotoFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = robotoFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = robotoFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = robotoFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = robotoFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = robotoFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = robotoFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = robotoFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = robotoFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = robotoFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = robotoFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = robotoFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = robotoFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = robotoFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = robotoFontFamily)
)