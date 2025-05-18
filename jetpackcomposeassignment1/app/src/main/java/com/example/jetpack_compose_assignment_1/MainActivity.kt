package com.example.jetpack_compose_assignment_1.com.example.courseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

// Data model
data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)

// Sample data
val sampleCourses = listOf(
    Course(
        "Introduction to Computer Science",
        "CS101",
        3,
        "An overview of computer science principles and programming.",
        "None"
    ),
    Course(
        "Data Structures",
        "CS201",
        4,
        "Covers arrays, stacks, queues, trees, and more.",
        "CS101"
    ),
    Course(
        "Operating Systems",
        "CS301",
        4,
        "Concepts of processes, threads, scheduling, and memory management.",
        "CS201"
    )
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
    MaterialTheme(
        colorScheme = lightColorScheme() // use darkColorScheme() for dark theme
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            CourseList(sampleCourses)
        }
    }
}

// List Composable
@Composable
fun CourseList(courses: List<Course>) {
    LazyColumn {
        items(courses) { course ->
            ExpandableCourseCard(course)
        }
    }
}

// Expandable Card Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCourseCard(course: Course) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(course.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Code: ${course.code}", style = MaterialTheme.typography.bodyMedium)
            Text("Credits: ${course.creditHours}", style = MaterialTheme.typography.bodyMedium)

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Description: ${course.description}", style = MaterialTheme.typography.bodySmall)
                Text("Prerequisites: ${course.prerequisites}", style = MaterialTheme.typography.bodySmall)
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
