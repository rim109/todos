package com.example.todo.domain.todos.dto

import com.example.todo.domain.comments.model.Comments
import java.util.Date

data class TodosResponse(
    val id: Long,
    var title: String,
    var description: String?,
    val day: Date,
    var nickname: String,
    var complete: Boolean,
    var commentList: List<Comments>
)
