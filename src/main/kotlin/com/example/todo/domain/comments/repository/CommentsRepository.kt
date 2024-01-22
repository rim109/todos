package com.example.todo.domain.comments.repository


import com.example.todo.domain.comments.model.Comments
import org.springframework.data.jpa.repository.JpaRepository

interface CommentsRepository : JpaRepository<Comments, Long> {
    //    todo로 댓글 조회
    fun findByTodoId(todosId: Long): List<Comments>
}