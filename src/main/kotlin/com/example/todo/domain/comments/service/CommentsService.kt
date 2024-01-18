package com.example.todo.domain.comments.service

import com.example.todo.domain.comments.dto.CreateCommentsRequest
import com.example.todo.domain.comments.dto.CommentsResponse
import com.example.todo.domain.comments.dto.DeleteCommentsRequest
import com.example.todo.domain.comments.dto.UpdateCommentsRequest

interface CommentsService {

    fun createComments(todosId: Long, request: CreateCommentsRequest, userId: Long): CommentsResponse

    fun updateComments(todosId: Long, commentsId: Long, request: UpdateCommentsRequest, userId: Long): CommentsResponse

    fun deleteComments(todosId: Long, commentsId: Long, request: DeleteCommentsRequest, userId: Long)
}