package com.example.todo.domain.todos.model

import com.example.todo.common.model.BaseTime
import com.example.todo.domain.comments.model.Comments
import com.example.todo.domain.comments.model.toResponse
import com.example.todo.domain.todos.dto.TodosResponse
import com.example.todo.domain.user.model.Users
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "todo")
class Todos(

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "complete")
    var complete: Boolean = false,

    @JsonIgnore
    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comment: MutableList<Comments> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: Users

) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComments(comments: Comments) {
        comment.add(comments)
    }

    fun removeComments(comments: Comments) {
        comment.remove(comments)
    }
}

fun Todos.toResponse(): TodosResponse {
    return TodosResponse(
        id = id!!,
        userId = user.id!!,
        title = title,
        description = description,
        nickname = nickname,
        complete = complete,
        comments = comment.map { it.toResponse() },
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}