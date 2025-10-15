package com.ansssiaz.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ansssiaz.component.theme.Green

@Composable
fun SortSection(
    isSorted: Boolean,
    onSortClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        SortButton(isSorted, onSortClick)
        Spacer(modifier = Modifier.size(4.dp))
        SortIcon()
    }
    Spacer(modifier = Modifier.size(8.dp))
}

@Composable
fun SortButton(
    isSorted: Boolean,
    onSortClick: () -> Unit
) {
    Text(
        text = if (isSorted) {
            stringResource(id = R.string.by_default)
        } else {
            stringResource(id = R.string.by_date_added)
        },
        modifier = Modifier.clickable { onSortClick() },
        color = Green,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
    )
}

@Composable
fun SortIcon() {
    Box(
        modifier = Modifier
            .size(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.sort),
            modifier = Modifier.size(16.dp),
            contentDescription = null,
            tint = Green
        )
    }
}