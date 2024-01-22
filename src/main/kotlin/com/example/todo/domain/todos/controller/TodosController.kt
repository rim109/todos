package com.example.todo.domain.todos.controller

import com.example.todo.common.dto.CustomUser
import com.example.todo.domain.todos.dto.*
import com.example.todo.domain.todos.service.TodosService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos")
@RestController
class TodosController(
    private val todosService: TodosService
) {

    @GetMapping
    fun getTodosList(): ResponseEntity<List<TodosResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todosService.getTodosList())
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todosService.getTodo(todoId))
    }

    @PreAuthorize("hasRole('MINE')")
    @PostMapping
    fun createTodo(
        @AuthenticationPrincipal user: CustomUser,
        @RequestBody createTodosRequest: CreateTodosRequest
    ): ResponseEntity<TodosResponse> {
        val userId = user.id
        val todosResponse = todosService.createTodo(createTodosRequest, userId)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todosResponse)
    }

    @PreAuthorize("hasRole('MINE')")
    @PutMapping("/{todoId}")
    fun updateTodos(
        @AuthenticationPrincipal user: CustomUser,
        @PathVariable todoId: Long, updateTodosRequest: UpdateTodosRequest
    ): ResponseEntity<TodosResponse> {
        val userId = user.id
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todosService.updateTodo(todoId, userId, updateTodosRequest))
    }

    @PreAuthorize("hasRole('MINE')")
    @PatchMapping("/{todoId}")
    fun completeStatus(@PathVariable todoId: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(todosService.isCompleteStatus(todoId))
    }


    @PreAuthorize("hasRole('MINE')")
    @DeleteMapping("/{todoId}")
    fun deleteTodos(
        @AuthenticationPrincipal user: CustomUser,
        @PathVariable todoId: Long
    ): ResponseEntity<Unit> {
        val userId = user.id
        todosService.deleteTodo(todoId, userId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}