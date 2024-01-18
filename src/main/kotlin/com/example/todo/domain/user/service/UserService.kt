package com.example.todo.domain.user.service

import com.example.todo.common.auth.TokenInfo
import com.example.todo.domain.user.dto.*

interface UserService {

    fun signup(signupRequest: SignupRequest): String

    fun login(loginRequest: LoginRequest): TokenInfo
}