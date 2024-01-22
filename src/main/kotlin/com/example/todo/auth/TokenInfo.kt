package com.example.todo.auth

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
)