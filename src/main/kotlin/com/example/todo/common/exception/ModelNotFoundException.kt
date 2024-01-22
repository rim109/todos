package com.example.todo.common.exception

data class ModelNotFoundException(val modelName: String, val id: Long) : RuntimeException(
    " $modelName not found with given id($id)"
) {
}