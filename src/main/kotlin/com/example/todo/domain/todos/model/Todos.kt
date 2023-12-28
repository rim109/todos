package com.example.todo.domain.todos.model

import com.example.todo.domain.todos.dto.TodosResponse
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "todos")
class Todos(

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "day")
    val day: Date
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun Todos.toResponse(): TodosResponse {
    return TodosResponse(
        id = id!!,
        title = title,
        description = description,
        day = day,
        nickname = nickname
    )
}