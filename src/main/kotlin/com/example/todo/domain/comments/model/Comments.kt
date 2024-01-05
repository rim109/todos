package com.example.todo.domain.comments.model

import com.example.todo.domain.comments.dto.CommentsResponse
import com.example.todo.domain.todos.model.Todos
import jakarta.persistence.*


@Entity
@Table(name = "comments")
class Comments(

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "commented")
    var commented: String? = null,

    @Column(name = "password")
    var password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todos

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comments.toResponse(): CommentsResponse {
    return CommentsResponse(
        id = id!!,
        nickname = nickname,
        commented= commented
    )
}