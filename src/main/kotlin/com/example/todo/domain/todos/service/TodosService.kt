package com.example.todo.domain.todos.service

import com.example.todo.domain.todos.dto.CreateTodosRequest
import com.example.todo.domain.todos.dto.TodosResponse
import com.example.todo.domain.todos.dto.UpdateTodosRequest

interface TodosService {

    fun getTodosList(): List<TodosResponse>

    fun getTodo(todosId: Long): TodosResponse

    fun createTodo(request: CreateTodosRequest): TodosResponse

    fun updateTodo(todosId: Long, request: UpdateTodosRequest): TodosResponse

    fun deleteTodo(todosId: Long)
}