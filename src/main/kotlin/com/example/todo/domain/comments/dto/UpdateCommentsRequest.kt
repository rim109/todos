package com.example.todo.domain.comments.dto

data class UpdateCommentsRequest(
    val nickname: String,
    val password: String,
    val commented: String?
)