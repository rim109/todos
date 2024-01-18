package com.example.todo.domain.user.dto

import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val role: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
