package com.example.todo.domain.comments.dto

data class CreateCommentsRequest(
    val nickname: String,
    val password: String,
    val commented: String?,
)