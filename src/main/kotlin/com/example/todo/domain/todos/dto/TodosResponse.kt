package com.example.todo.domain.todos.dto

import java.util.Date

data class TodosResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val day: Date,
    val nickname: String
)
