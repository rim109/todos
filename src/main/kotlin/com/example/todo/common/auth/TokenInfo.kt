package com.example.todo.common.auth

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
)