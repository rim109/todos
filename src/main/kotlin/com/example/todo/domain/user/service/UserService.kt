package com.example.todo.domain.user.service

import com.example.todo.domain.user.dto.*

interface UserService {

    fun signup(signupRequest: SignupRequest): UserResponse

    fun login(loginRequest: LoginRequest): LoginResponse
}