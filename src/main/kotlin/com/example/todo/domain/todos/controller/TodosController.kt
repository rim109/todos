package com.example.todo.domain.todos.controller

import com.example.todo.domain.todos.dto.*
import com.example.todo.domain.todos.service.TodosService
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
    fun getTodo(@PathVariable todoId: Long) : ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todosService.getTodo(todoId))
    }
    @PreAuthorize("hasRole('MYSELF')")
    @PostMapping
    fun createTodo(
        @RequestBody createTodosRequest: CreateTodosRequest
    ): ResponseEntity<TodosResponse> {
        val todosResponse = todosService.createTodo(createTodosRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todosResponse)
    }

    @PreAuthorize("hasRole('MYSELF')")
    @PutMapping("/{todoId}")
    fun updateTodos(
        @PathVariable todoId: Long, updateTodosRequest: UpdateTodosRequest
    ): ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todosService.updateTodo(todoId,updateTodosRequest))
    }

    @PreAuthorize("hasRole('MYSELF')")
    @PatchMapping("/{todoId}")
    fun completeStatus(@PathVariable todoId: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(todosService.isCompleteStatus(todoId))
    }


    @PreAuthorize("hasRole('MYSELF')")
    @DeleteMapping("/{todoId}")
    fun deleteTodos(
        @PathVariable todoId: Long
    ): ResponseEntity<Unit> {
        todosService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}