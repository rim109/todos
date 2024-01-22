package com.example.todo.domain.user.controller

import com.example.todo.common.dto.BaseResponse
import com.example.todo.auth.TokenInfo
import com.example.todo.domain.user.dto.*
import com.example.todo.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid signupRequest: SignupRequest): BaseResponse<Unit> {
        val resultMsg: String = userService.signup(signupRequest)
        return BaseResponse(message = resultMsg)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): BaseResponse<TokenInfo> {
        val tokenInfo = userService.login(loginRequest)
        return BaseResponse(data = tokenInfo)
    }

}