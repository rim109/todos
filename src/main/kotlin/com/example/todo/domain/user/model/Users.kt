package com.example.todo.domain.user.model

import com.example.todo.common.model.BaseTime
import com.example.todo.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["email"])]
)
class Users(
    email: String,
    password: String,
    nickname: String,
    role: UserRole
) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, length = 30, updatable = false)
    var email = email

    @Column(nullable = false, length = 100)
    var password = password

    @Column(nullable = false, length = 10)
    var nickname = nickname

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role = role

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    val userRole: List<UserRoleEntity>? = null
}

fun Users.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        nickname = nickname,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        role = role.name
    )
}