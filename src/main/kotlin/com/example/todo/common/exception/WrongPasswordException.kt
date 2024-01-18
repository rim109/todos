package com.example.todo.common.exception

data class WrongPasswordException(val password: String, val id: Long) : RuntimeException(
    " $password not found with given id($id)"
) {
}