package com.example.todo.domain.comments.model

import com.example.todo.common.model.BaseTime
import com.example.todo.domain.comments.dto.CommentsResponse
import com.example.todo.domain.todos.model.Todos
import com.example.todo.domain.user.model.Users
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(name = "comments")
class Comments(

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "commented")
    var commented: String? = null,

    @Column(name = "password")
    var password: String,

    @ManyToOne
    @JoinColumn(name = "todo_id")
    val todo: Todos,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    val user: Users

) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comments.toResponse(): CommentsResponse {
    return CommentsResponse(
        id = id!!,
        nickname = nickname,
        commented = commented,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}