package com.ansssiaz.feature.log_in

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ansssiaz.component.theme.Green
import com.ansssiaz.component.theme.White

@Composable
fun LogInFooter() {
    Spacer(modifier = Modifier.size(16.dp))

    SignUpRow()

    ForgotPasswordText()

    Spacer(modifier = Modifier.size(32.dp))

    //SocialButtonsGroup()
}

@Composable
private fun SignUpRow(){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = R.string.dont_have_account),
            color = White,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(id = R.string.sign_up),
            color = Green,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .clickable { }
                .padding(start = 4.dp)
        )
    }
}

@Composable
private fun ForgotPasswordText(){
    Text(
        text = stringResource(id = R.string.forgot_password),
        color = Green,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.clickable { }
    )
}