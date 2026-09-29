package com.example.labul2pam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Blue = Color(0xFF3F5BFF)
private val Ink = Color(0xFF20223D)
private val Orange = Color(0xFFFF700D)
private val PaleText = Color(0xFFA5A7BD)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // false = Home; true = pagina cursurilor
            var showCourses by remember { mutableStateOf(false) }

            // Butonul Back al telefonului revine la Home.
            BackHandler(enabled = showCourses) {
                showCourses = false
            }

            MaterialTheme {
                if (showCourses) {
                    CoursesScreen(onBack = { showCourses = false })
                } else {
                    HomeScreen(onOpenCourses = { showCourses = true })
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onOpenCourses: () -> Unit) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val firstBannerWidth = maxWidth * 0.72f
        val secondBannerWidth = maxWidth * 0.44f

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(181.dp)
                            .background(Blue)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, top = 60.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    "Hi, Kristin",
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Let's start learning",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            Image(
                                painter = painterResource(R.drawable.avatar),
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(51.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(13.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 16.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Learned today",
                                    color = PaleText,
                                    fontSize = 13.sp
                                )

                                // Singurul buton care deschide pagina cursurilor.
                                Text(
                                    "My courses",
                                    modifier = Modifier.clickable {
                                        onOpenCourses()
                                    },
                                    color = Blue,
                                    fontSize = 13.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(7.dp))

                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    "46min",
                                    color = Ink,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    " / 60min",
                                    color = PaleText,
                                    fontSize = 12.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            LinearProgressIndicator(
                                progress = { 46f / 60f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(CircleShape),
                                color = Orange,
                                trackColor = Color(0xFFF0EFFA)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Image(
                            painter = painterResource(R.drawable.learning_banner),
                            contentDescription = "Learning banner",
                            modifier = Modifier
                                .width(firstBannerWidth)
                                .height(185.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    item {
                        Image(
                            painter = painterResource(R.drawable.learning_side),
                            contentDescription = "Learning side",
                            modifier = Modifier
                                .width(secondBannerWidth)
                                .height(185.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(29.dp))

                Text(
                    "Learning Plan",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Ink,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 17.dp,
                            vertical = 28.dp
                        )
                    ) {
                        PlanRow("Packaging Design", 40, 48)
                        Spacer(modifier = Modifier.height(24.dp))
                        PlanRow("Product Design", 6, 24)
                    }
                }

                Spacer(modifier = Modifier.height(23.dp))

                Image(
                    painter = painterResource(R.drawable.meetup),
                    contentDescription = "Meetup",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(204.dp),
                    contentScale = ContentScale.FillBounds
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun PlanRow(title: String, completed: Int, total: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            progress = { completed.toFloat() / total },
            modifier = Modifier.size(26.dp),
            color = Color(0xFF777777),
            trackColor = Color(0xFFF0EFFA),
            strokeWidth = 2.5.dp
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            title,
            modifier = Modifier.weight(1f),
            color = Ink,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Text("$completed", color = Ink, fontSize = 14.sp)
        Text("/$total", color = PaleText, fontSize = 14.sp)
    }
}

private data class CourseInfo(
    val title: String,
    val author: String,
    val price: String,
    val hours: String
)

@Composable
fun CoursesScreen(onBack: () -> Unit) {
    var query by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("All") }

    val courses = listOf(
        CourseInfo("Product Design v1.0", "Robertson Connie", "$190", "16 hours"),
        CourseInfo("Java Development", "Nguyen Shane", "$190", "16 hours"),
        CourseInfo("Visual Design", "Bert Pullman", "$250", "14 hours")
    )

    // Căutarea verifică titlul fiecărui curs.
    val visibleCourses = courses.filter { course ->
        course.title.contains(query, ignoreCase = true)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val categoryWidth = (maxWidth - 52.dp) / 2

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 28.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Săgeata vizibilă: la apăsare revine la Home.
                    Text(
                        "‹",
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(end = 16.dp),
                        color = Ink,
                        fontSize = 33.sp
                    )

                    Text(
                        "Course",
                        modifier = Modifier.weight(1f),
                        color = Ink,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Image(
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    textStyle = TextStyle(color = Ink, fontSize = 15.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(55.dp)
                        .clip(RoundedCornerShape(11.dp))
                        .background(Color(0xFFF5F4FC)),
                    decorationBox = { textField ->
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("⌕", color = PaleText, fontSize = 25.sp)
                            Spacer(modifier = Modifier.width(10.dp))

                            Box(modifier = Modifier.weight(1f)) {
                                if (query.isEmpty()) {
                                    Text(
                                        "Find Course",
                                        color = PaleText,
                                        fontSize = 14.sp
                                    )
                                }
                                textField()
                            }

                            Text("☷", color = PaleText, fontSize = 24.sp)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(31.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Image(
                            painter = painterResource(R.drawable.language),
                            contentDescription = "Language",
                            modifier = Modifier
                                .width(categoryWidth)
                                .height(110.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    item {
                        Image(
                            painter = painterResource(R.drawable.painting),
                            contentDescription = "Painting",
                            modifier = Modifier
                                .width(categoryWidth)
                                .height(110.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                Spacer(modifier = Modifier.height(31.dp))

                Text(
                    "Choice your course",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Ink,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf("All", "Popular", "New").forEach { tab ->
                        Text(
                            text = tab,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    if (selectedTab == tab) Blue
                                    else Color.Transparent
                                )
                                .clickable { selectedTab = tab }
                                .padding(horizontal = 22.dp, vertical = 8.dp),
                            color = if (selectedTab == tab) Color.White else PaleText,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            items(visibleCourses.size) { index ->
                CourseCard(visibleCourses[index])
            }
        }
    }
}

@Composable
private fun CourseCard(course: CourseInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(79.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFCACACA))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    course.title,
                    color = Ink,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    "♟  ${course.author}",
                    color = PaleText,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(7.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        course.price,
                        color = Blue,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        course.hours,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(0xFFFFF0E9))
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                        color = Orange,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}