package com.example.todo.domain.exception

data class WrongPasswordException(val password: String, val id: Long) : RuntimeException(
    " $password not found with given id($id)"
) {
}