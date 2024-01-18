package com.example.todo.domain.user.repository

import com.example.todo.domain.user.model.UserRoleEntity
import com.example.todo.domain.user.model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users,Long> {
    fun findByEmail(email: String): Users?

//    fun existsByEmail(email: String): Boolean
}

interface UserRoleRepository : JpaRepository<UserRoleEntity, Long> {
}