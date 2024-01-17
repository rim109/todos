package com.example.todo.domain.comments.dto

import java.time.LocalDateTime

data class CommentsResponse(
    val id: Long,
    val nickname: String,
    val commented: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
