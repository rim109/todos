package com.example.todo.domain.comments.dto

data class CommentsResponse(
    val id: Long,
    val nickname: String,
    val comment: String?,
)
