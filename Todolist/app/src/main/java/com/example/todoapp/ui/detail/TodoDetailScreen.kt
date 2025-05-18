package com.example.todoapp.ui.detail

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TodoDetailScreen(todoId: Int, viewModel: TodoDetailViewModel) {
    // Use a lighter shade of blue
    val cardBackgroundColor = Color(0xFFE3F2FD) // Lighter blue

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // Background of the entire screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center), // Center the Card
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally within the Column
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth() // Make the card take up the full available width
                    .padding(16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp), // Add elevation for a shadow
                // Removed colors = CardDefaults.cardColor(containerColor = cardBackgroundColor)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),  // Ensure the Surface fills the Card
                    color = cardBackgroundColor
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Title: ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(100.dp) // Add a fixed width
                            )
                            Text(
                                text = viewModel.getTodoById(todoId)?.title ?: "N/A",
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "User ID: ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(100.dp)
                            )
                            Text(
                                text = viewModel.getTodoById(todoId)?.userId?.toString() ?: "N/A",
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Status: ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(100.dp)
                            )
                            Text(
                                text = if (viewModel.getTodoById(todoId)?.completed == true) "Completed" else "Pending",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
            if (viewModel.getTodoById(todoId) == null) {
                Text(
                    text = "Todo not found",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

