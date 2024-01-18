package com.example.todo.domain.todos.dto

import com.example.todo.domain.comments.dto.CommentsResponse
import java.time.LocalDateTime
import java.util.Date

data class TodosResponse(
    val id: Long,
    var title: String,
    var description: String?,
    var nickname: String,
    var complete: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    var comments: List<CommentsResponse>
)