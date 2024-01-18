package com.example.todo.domain.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class SignupRequest(
    @field: NotBlank
    @field: Pattern(
        regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$",
        message = "이메일의 형식에 맞게 입력해주세요"
    )
    @JsonProperty("email")
    private val _email: String?,

    @field: NotBlank
    @field: Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "영문, 숫자, 특수문자를 포함한 8~20자리로 입력해주세요"
    )
    @JsonProperty("password")
    private val _password: String?,
    val nickname: String,
    val role: String
){
    val email: String
        get() = _email!!
    val password: String
        get() = _password!!
}


