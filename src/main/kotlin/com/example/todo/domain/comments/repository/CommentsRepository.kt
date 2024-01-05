package com.example.todo.domain.comments.repository


import com.example.todo.domain.comments.model.Comments
import org.springframework.data.jpa.repository.JpaRepository

interface CommentsRepository : JpaRepository<Comments, Long> {
}