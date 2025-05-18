package com.example.jetpack_compose_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme

// Data model
data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)

// Sample data with different colors
val sampleCoursesWithColors = listOf(
    Course(
        "Introduction to Computer Science",
        "CS101",
        3,
        "An overview of computer science principles and programming.",
        "None"
    ) to Color(0xFFE0F7FA), // Light Cyan
    Course(
        "Data Structures",
        "CS201",
        4,
        "Covers arrays, stacks, queues, trees, and more.",
        "CS101"
    ) to Color(0xFFFFF9C4), // Light Yellow
    Course(
        "Operating Systems",
        "CS301",
        4,
        "Concepts of processes, threads, scheduling, and memory management.",
        "CS201"
    ) to Color(0xFFF0F4C3), // Light Lime
    Course(
        "Mobile Application  and \nDevelopment",
        "CS305",
        4,
        "Concepts of koltin  and flutter",
        "CS206"
    ) to Color(0xFFDCE775), // Lime Accent
    Course(
        "Cybersecurity",
        "CS310",
        4,
        " concepts of hashing and encyption",
        "CS207"
    ) to Color(0xFFAED581), // Light Green
    Course(
        "Artifical Intellegince",
        "CS301",
        4,
        "Concepts of introduction to AI and machine learning.",
        "CS201"
    ) to Color(0xFF81C784) // Green Accent
)

// Main activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseApp()
        }
    }
}

// App Composable
@Composable
fun CourseApp() {
    var showCourseList by remember { mutableStateOf(false) }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            if (showCourseList) {
                CourseListScreen(coursesWithColors = sampleCoursesWithColors)
            } else {
                HomeScreen(onContinueClicked = { showCourseList = true })
            }
        }
    }
}

// Home Screen Composable
@Composable
fun HomeScreen(onContinueClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AAIT Course Lists",
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onContinueClicked) {
            Text("Continue")
        }
    }
}

// Course List Screen Composable
@Composable
fun CourseListScreen(coursesWithColors: List<Pair<Course, Color>>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "SITE COURSES",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Added top padding here
            textAlign = TextAlign.Center
        )
        CourseList(coursesWithColors = coursesWithColors)
    }
}

// List Composable
@Composable
fun CourseList(coursesWithColors: List<Pair<Course, Color>>) {
    LazyColumn(
        contentPadding = PaddingValues(top = 16.dp, bottom = 8.dp)
    ) {
        items(coursesWithColors) { (course, color) ->
            ExpandableCourseCard(course = course, backgroundColor = color)
        }
    }
}

// Expandable Card Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCourseCard(course: Course, backgroundColor: Color) {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearEasing
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor // Set the background color here
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Add some elevation for separation
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable { expanded = !expanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(course.title, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Code: ${course.code}", style = MaterialTheme.typography.bodyMedium)
                    Text("Credits: ${course.creditHours}", style = MaterialTheme.typography.bodyMedium)
                }
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                    contentDescription = if (expanded) "Show Less" else "Show More"
                )
            }

            if (expanded) {
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Description: ${course.description}", style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Prerequisites: ${course.prerequisites}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

// Preview for Android Studio
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CourseApp()
}