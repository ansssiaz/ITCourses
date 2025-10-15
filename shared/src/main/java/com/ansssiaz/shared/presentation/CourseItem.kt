package com.ansssiaz.shared.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ansssiaz.component.theme.DarkGray
import com.ansssiaz.component.theme.ExtraLightGray
import com.ansssiaz.component.theme.Green
import com.ansssiaz.component.theme.White
import com.ansssiaz.shared.R
import com.ansssiaz.shared.domain.Course

@Composable
fun CourseItem(
    course: Course,
    onAddToFavouritesClick: (Course) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkGray)
    ) {
        Column {
            CourseImageSection(
                course = course,
                onAddToFavouritesClick = onAddToFavouritesClick
                )
            CourseInformationSection(course)
        }
    }
}

@Composable
fun CourseImageSection(
    course: Course,
    onAddToFavouritesClick: (Course) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(114.dp)
    ) {
        CourseImage(index = course.imageId)
        FavouriteButton(
            course = course,
            onAddToFavouritesClick = { onAddToFavouritesClick(course) },
            modifier = Modifier.align(Alignment.TopEnd)
        )
        CourseBadges(
            course = course,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Composable
fun CourseImage(index: Int) {
    Image(
        painter = painterResource(index),
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp)),
        alignment = Alignment.TopCenter,
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun FavouriteButton(
    course: Course,
    onAddToFavouritesClick: (Course) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .size(28.dp)
            .clickable { onAddToFavouritesClick(course) }
            .background(
                color = Color.Black.copy(alpha = 0.3f),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = if (course.hasLike) {
                painterResource(id = R.drawable.filled_bookmark)
            } else {
                painterResource(id = R.drawable.bookmark)
            },
            modifier = Modifier.size(16.dp),
            contentDescription = null,
            tint = if (course.hasLike) Green else White
        )
    }
}

@Composable
fun CourseBadges(
    course: Course,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(start = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        RatingBadge(course.rate)
        DateBadge(course.publishDate)
    }
}

@Composable
fun RatingBadge(rating: String) {
    Box(
        modifier = Modifier
            .background(
                color = DarkGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                tint = Green
            )
            Text(
                text = rating,
                color = White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun DateBadge(publishDate: String) {
    Box(
        modifier = Modifier
            .background(
                color = DarkGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 6.dp)

    ) {
        Text(
            modifier = Modifier.padding(horizontal = 6.dp),
            text = publishDate,
            color = White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun CourseInformationSection(course: Course) {
    Column(
        modifier = Modifier.padding(
            top = 16.dp,
            start = 16.dp,
            bottom = 16.dp,
            end = 12.dp
        )
    ) {
        CourseTitle(course.title)
        Spacer(modifier = Modifier.size(12.dp))
        CourseDescription(course.text)
        Spacer(modifier = Modifier.size(10.dp))
        CourseFooter(course.price)
    }
}

@Composable
fun CourseTitle(title: String) {
    Text(
        text = title,
        color = White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun CourseDescription(description: String) {
    Text(
        text = description,
        color = ExtraLightGray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        maxLines = 2,
        lineHeight = 16.sp,
        overflow = TextOverflow.Ellipsis,
        letterSpacing = 0.4.sp,
        textAlign = TextAlign.Left
    )
}

@Composable
fun CourseFooter(price: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$price ₽",
            color = White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = stringResource(id = R.string.more),
                color = Green,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_right),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null,
                    tint = Green
                )
            }
        }
    }
}