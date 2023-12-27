package com.example.todo.domain.todos.service

import ch.qos.logback.core.model.processor.ModelHandlerException
import com.example.todo.domain.exception.ModelNotFoundException
import com.example.todo.domain.todos.dto.CreateTodosRequest
import com.example.todo.domain.todos.dto.TodosResponse
import com.example.todo.domain.todos.dto.UpdateTodosRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodosServiceImpl: TodosService {
    override fun getTodosList(): List<TodosResponse> {
        TODO("Not yet implemented")
    }

    override fun getTodo(todosId: Long): TodosResponse {
        TODO("Not yet implemented")
        throw ModelNotFoundException (modelName = "Todos", id = 1L)
    }

    @Transactional
    override fun createTodo(request: CreateTodosRequest): TodosResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateTodo(todosId: Long, request: UpdateTodosRequest): TodosResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteTodo(todosId: Long) {
        TODO("Not yet implemented")
    }


}