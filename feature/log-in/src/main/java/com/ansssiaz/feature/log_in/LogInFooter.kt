package com.ansssiaz.feature.log_in

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ansssiaz.component.theme.Green
import com.ansssiaz.component.theme.Stroke
import com.ansssiaz.component.theme.White
import com.example.ui_components.GroupSocialButtons
import androidx.core.net.toUri

@Composable
fun LogInFooter() {
    val context = LocalContext.current

    Spacer(modifier = Modifier.size(16.dp))

    SignUpRow()

    ForgotPasswordText()

    Spacer(modifier = Modifier.size(32.dp))

    GroupSocialButtons(
        dividerColor = Stroke,
        onVkClick = { openUrl(context, "https://vk.com/") },
        onOkClick = { openUrl(context, "https://ok.ru/") })
}

@Composable
private fun SignUpRow() {
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
private fun ForgotPasswordText() {
    Text(
        text = stringResource(id = R.string.forgot_password),
        color = Green,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.clickable { }
    )
}

private fun openUrl(
    context: Context,
    url: String,
) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(
            context,
            context.getString(R.string.browser_not_found),
            Toast.LENGTH_SHORT
        ).show()
    } catch (_: Exception) {
        Toast.makeText(
            context,
            context.getString(R.string.couldnt_open_link),
            Toast.LENGTH_SHORT
        ).show()
    }
}