package com.example.todo.domain.todos.dto

import com.example.todo.domain.comments.model.Comments
import java.util.*

data class CreateTodosRequest(
    val nickname: String,
    val title: String,
    val description: String?,
    val day: Date
)