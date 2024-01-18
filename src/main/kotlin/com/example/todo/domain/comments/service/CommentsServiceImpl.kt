package com.example.todo.domain.comments.service

import com.example.todo.common.exception.IllegalStateException
import com.example.todo.common.exception.ModelNotFoundException
import com.example.todo.domain.comments.dto.CreateCommentsRequest
import com.example.todo.domain.comments.dto.CommentsResponse
import com.example.todo.domain.comments.dto.DeleteCommentsRequest
import com.example.todo.domain.comments.dto.UpdateCommentsRequest
import com.example.todo.domain.comments.model.Comments
import com.example.todo.domain.comments.model.toResponse
import com.example.todo.domain.comments.repository.CommentsRepository
import com.example.todo.common.exception.WrongPasswordException
import com.example.todo.domain.todos.repository.TodoRepository
import com.example.todo.domain.user.model.Users
import com.example.todo.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentsServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentsRepository: CommentsRepository,
    private val userRepository: UserRepository
) : CommentsService {

    // comment 생성
    @Transactional
    override fun createComments(todosId:Long, request: CreateCommentsRequest, userId: Long): CommentsResponse {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)
        val user: Users = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        val comment = Comments(
            nickname = request.nickname,
            password = request.password,
            commented = request.commented,
            todo = todos,
            user = user
        )
        todos.addComments(comment)
        commentsRepository.save(comment)
        return comment.toResponse()
    }

    //comment 수정
    @Transactional
    override fun updateComments(todosId:Long, commentsId: Long, request: UpdateCommentsRequest, userId: Long): CommentsResponse {
        val comment =
            commentsRepository.findByIdOrNull(commentsId) ?: throw IllegalStateException("Comments", commentsId)

        if (comment.password != request.password)
            throw WrongPasswordException("Comments", commentsId)
        else {
            comment.nickname = request.nickname
            comment.commented = request.commented
            commentsRepository.save(comment)
        }

        return commentsRepository.save(comment).toResponse()
    }

    //comment 삭제
    @Transactional
    override fun deleteComments(todosId:Long, commentsId: Long, request: DeleteCommentsRequest, userId: Long){
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)
        val comment =
            commentsRepository.findByIdOrNull(commentsId) ?: throw IllegalStateException("Comments", commentsId)

        if (comment.password != request.password)
            throw WrongPasswordException("Comments", commentsId)
        else {
            todos.removeComments(comment)
            commentsRepository.save(comment)
        }
    }


}