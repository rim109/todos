package com.example.todo.domain.todos.dto

import java.util.*

data class CreateTodosRequest(
    val nickname: String,
    val title: String,
    val description: String?,
    val day: Date
)