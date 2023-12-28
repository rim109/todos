package com.example.todo.domain.todos.repository

import com.example.todo.domain.todos.model.Todos
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todos, Long> {
}