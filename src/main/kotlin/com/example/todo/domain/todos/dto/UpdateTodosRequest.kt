package com.example.todo.domain.todos.dto

data class UpdateTodosRequest(
    val nickname: String,
    val title: String,
    val description: String?
)