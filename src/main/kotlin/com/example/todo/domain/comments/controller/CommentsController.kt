package com.example.todo.domain.comments.controller

import com.example.todo.common.dto.CustomUser
import com.example.todo.domain.comments.dto.CommentsResponse
import com.example.todo.domain.comments.dto.CreateCommentsRequest
import com.example.todo.domain.comments.dto.DeleteCommentsRequest
import com.example.todo.domain.comments.dto.UpdateCommentsRequest
import com.example.todo.domain.comments.service.CommentsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RequestMapping("/comments/{todosId}")
@RestController
class CommentsController(
    private val commentsService: CommentsService,
) {

    @PostMapping
    fun createComments(
        @AuthenticationPrincipal user: CustomUser,
        @PathVariable todosId:Long,
        @RequestBody createCommentsRequest: CreateCommentsRequest
    ): ResponseEntity<CommentsResponse> {
        val userId = user.id
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentsService.createComments(todosId,createCommentsRequest, userId))
    }

    @PutMapping("/{commentsId}")
    fun updateComments(
        @AuthenticationPrincipal user: CustomUser,
        @PathVariable todosId:Long,
        @PathVariable commentsId: Long,
        updateCommentsRequest: UpdateCommentsRequest
    ): ResponseEntity<CommentsResponse> {
        val userId = user.id
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentsService.updateComments(todosId, commentsId, updateCommentsRequest, userId))
    }

    @DeleteMapping("/{commentsId}")
    fun deleteComments(
        @AuthenticationPrincipal user: CustomUser,
        @PathVariable todosId:Long,
        @PathVariable commentsId: Long, deleteCommentsRequest: DeleteCommentsRequest
    ): ResponseEntity<Unit> {
        val userId = user.id
        commentsService.deleteComments(todosId, commentsId, deleteCommentsRequest, userId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}