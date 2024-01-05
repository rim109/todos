package com.example.todo.domain.todos.model

import com.example.todo.domain.comments.model.Comments
import com.example.todo.domain.todos.dto.TodosResponse
import com.fasterxml.jackson.annotation.JsonIgnore
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
    val day: Date,

    @Column(name = "complete")
    var complete: Boolean = false,
//
//    @Transient
//    val commentList: List<Comments>,

    @JsonIgnore
    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comment: MutableList<Comments> = mutableListOf()

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComments(comments: Comments){
        comment.add(comments)
    }
    fun removeComments(comments: Comments){
        comment.remove(comments)
    }
}

fun Todos.toResponse(): TodosResponse {
    return TodosResponse(
        id = id!!,
        title = title,
        description = description,
        day = day,
        nickname = nickname,
        complete = complete,
        commentList = comment
    )
}