package com.example.todo.domain.todos.controller

import com.example.todo.domain.todos.dto.CreateTodosRequest
import com.example.todo.domain.todos.dto.TodosResponse
import com.example.todo.domain.todos.dto.UpdateTodosRequest
import com.example.todo.domain.todos.service.TodosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @PostMapping
    fun createTodo(@RequestBody createTodosRequest: CreateTodosRequest): ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todosService.createTodo(createTodosRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodos(@PathVariable todoId: Long, updateTodosRequest: UpdateTodosRequest): ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body((todosService.updateTodo(todoId, updateTodosRequest)))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodos(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todosService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}