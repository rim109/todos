package com.example.todo.domain.comments.controller

import com.example.todo.domain.comments.dto.CommentsResponse
import com.example.todo.domain.comments.dto.CreateCommentsRequest
import com.example.todo.domain.comments.dto.DeleteCommentsRequest
import com.example.todo.domain.comments.dto.UpdateCommentsRequest
import com.example.todo.domain.comments.service.CommentsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RequestMapping("/comments/{todosId}")
@RestController
class CommentsController(
    private val commentsService: CommentsService,
) {

    @PreAuthorize("hasRole('MYSELF')")
    @PostMapping
    fun createComments(
        @PathVariable todosId:Long,
        @RequestBody createCommentsRequest: CreateCommentsRequest
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentsService.createComments(todosId,createCommentsRequest))
    }

    @PreAuthorize("hasRole('MYSELF')")
    @PutMapping("/{commentsId}")
    fun updateComments(
        @PathVariable todosId:Long,
        @PathVariable commentsId: Long,
        updateCommentsRequest: UpdateCommentsRequest
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentsService.updateComments(todosId, commentsId, updateCommentsRequest))
    }

    @PreAuthorize("hasRole('MYSELF')")
    @DeleteMapping("/{commentsId}")
    fun deleteComments(
        @PathVariable todosId:Long,
        @PathVariable commentsId: Long, deleteCommentsRequest: DeleteCommentsRequest
    ): ResponseEntity<Unit> {
        commentsService.deleteComments(todosId, commentsId, deleteCommentsRequest)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}