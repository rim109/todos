package com.example.todo.domain.todos.service

import com.example.todo.domain.comments.model.Comments
import com.example.todo.domain.comments.repository.CommentsRepository
import com.example.todo.domain.exception.IllegalStateException
import com.example.todo.domain.exception.ModelNotFoundException
import com.example.todo.domain.todos.dto.CreateTodosRequest
import com.example.todo.domain.todos.dto.TodosResponse
import com.example.todo.domain.todos.dto.UpdateTodosRequest
import com.example.todo.domain.todos.model.Todos
import com.example.todo.domain.todos.model.toResponse
import com.example.todo.domain.todos.repository.TodoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodosServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentsRepository: CommentsRepository,
) : TodosService {

    // 전체 todo 조회
    override fun getTodosList(): List<TodosResponse> {
        return todoRepository.findAll(Sort.by(Sort.Direction.DESC,"day")).map { it.toResponse() }
    }

    // 선택된 todo 조회
    override fun getTodo(todosId: Long): TodosResponse {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw ModelNotFoundException("Todos", todosId)
        //todo로 댓글 조회
        var comment: List<Comments> = commentsRepository.findByTodoId(todosId)
        todos.comment.addAll(comment)

        return todos.toResponse()
    }

    // todo 생성
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

    //todo 수정
    @Transactional
    override fun updateTodo(todosId: Long, request: UpdateTodosRequest): TodosResponse {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)

//        val (title, description, nickname) = request
        todos.nickname = request.nickname
        todos.title = request.title
        todos.description = request.description

        return todoRepository.save(todos).toResponse()
//        return TodosResponse(1,"","", Date(),"" )
    }

    //todo 삭제
    @Transactional
    override fun deleteTodo(todosId: Long) {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)
        todoRepository.delete(todos)
    }

    //todo 완료여부
    override fun isCompleteStatus(todosId: Long) {
        val todos = todoRepository.findByIdOrNull(todosId) ?: throw IllegalStateException("Todos", todosId)
        todos.complete = !todos.complete
        todoRepository.save(todos)
    }
}