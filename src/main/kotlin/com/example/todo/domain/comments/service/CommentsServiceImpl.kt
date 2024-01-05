package com.example.todo.domain.comments.service

import com.example.todo.domain.exception.IllegalStateException
import com.example.todo.domain.exception.ModelNotFoundException
import com.example.todo.domain.comments.dto.CreateCommentsRequest
import com.example.todo.domain.comments.dto.CommentsResponse
import com.example.todo.domain.comments.dto.DeleteCommentsRequest
import com.example.todo.domain.comments.dto.UpdateCommentsRequest
import com.example.todo.domain.comments.model.Comments
import com.example.todo.domain.comments.model.toResponse
import com.example.todo.domain.comments.repository.CommentsRepository
import com.example.todo.domain.exception.WrongPasswordException
import com.example.todo.domain.todos.model.Todos
import com.example.todo.domain.todos.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentsServiceImpl(
    private val todoRepository: TodoRepository,
    private val CommentsRepository: CommentsRepository,
) : CommentsService {
    @Transactional
    override fun createComments(todosId:Long, request: CreateCommentsRequest): CommentsResponse {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)

        val comment = Comments(
            nickname = request.nickname,
            password = request.password,
            comment = request.comment,
            todo = todos
        )
        todos.addComments(comment)
        CommentsRepository.save(comment)
        return comment.toResponse()
    }

    @Transactional
    override fun updateComments(todosId:Long, commentsId: Long, request: UpdateCommentsRequest): CommentsResponse {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)
        val comment =
            CommentsRepository.findByIdOrNull(commentsId) ?: throw IllegalStateException("Comments", commentsId)

        if (comment.password != request.password)
            throw WrongPasswordException("Comments", commentsId)
        else {
            todos.comment.remove(comment)
            comment.nickname = request.nickname
            comment.comment = request.comment
            CommentsRepository.save(comment)
        }

        return CommentsRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComments(todosId:Long, commentsId: Long, request: DeleteCommentsRequest){
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)
        val comment =
            CommentsRepository.findByIdOrNull(commentsId) ?: throw IllegalStateException("Comments", commentsId)

        if (comment.password != request.password)
            throw WrongPasswordException("Comments", commentsId)
        else {
            todos.removeComments(comment)
            CommentsRepository.save(comment)
        }
    }


}