package com.example.todo.domain.todos.service

import com.example.todo.domain.exception.ModelNotFoundException
import com.example.todo.domain.todos.dto.CreateTodosRequest
import com.example.todo.domain.todos.dto.TodosResponse
import com.example.todo.domain.todos.dto.UpdateTodosRequest
import com.example.todo.domain.todos.model.Todos
import com.example.todo.domain.todos.model.toResponse
import com.example.todo.domain.todos.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodosServiceImpl(
    private val todoRepository: TodoRepository,
) : TodosService {
    override fun getTodosList(): List<TodosResponse> {
        return todoRepository.findAll().map { it.toResponse() }
    }

    override fun getTodo(todosId: Long): TodosResponse {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw ModelNotFoundException("Todos", todosId)
        return todos.toResponse()
    }

    @Transactional
    override fun createTodo(request: CreateTodosRequest): TodosResponse {
        return todoRepository.save(
            Todos(
                nickname = request.nickname,
                title = request.title,
                description = request.description,
                day = request.day
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todosId: Long, request: UpdateTodosRequest): TodosResponse {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw ModelNotFoundException("Todos", todosId)

//        val (title, description, nickname) = request
        todos.nickname = request.nickname
        todos.title = request.title
        todos.description = request.description

        return todoRepository.save(todos).toResponse()
    }

    @Transactional
    override fun deleteTodo(todosId: Long) {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw ModelNotFoundException("Todos", todosId)
        todoRepository.delete(todos)
    }
}