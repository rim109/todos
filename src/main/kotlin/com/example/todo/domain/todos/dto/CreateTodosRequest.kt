package com.example.todo.domain.todos.dto


data class CreateTodosRequest(
    val nickname: String,
    val title: String,
    val description: String?
)