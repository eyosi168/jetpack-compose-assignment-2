package com.example.todoapp.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.local.TodoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onItemClick: (Int) -> Unit
) {
    val backgroundColor = Color(0xFFE3F2FD) // Light blue background

    Surface(
        color = backgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            viewModel.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            viewModel.errorMessage.isNotEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = viewModel.errorMessage, color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.todos) { todo ->
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onItemClick(todo.id) },
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = todo.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = if (todo.completed) Icons.Filled.Done else Icons.Filled.Close,
                                        contentDescription = if (todo.completed) "Completed" else "Pending",
                                        tint = if (todo.completed) Color(0xFF2E7D32) else Color(0xFFC62828)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (todo.completed) "Completed" else "Pending",
                                        color = if (todo.completed) Color(0xFF2E7D32) else Color(0xFFC62828),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
