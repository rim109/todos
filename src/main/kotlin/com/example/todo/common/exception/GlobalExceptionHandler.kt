package com.example.todo.common.exception

import com.example.todo.common.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handlerModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handlerIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(WrongPasswordException::class)
    fun handlerWrongPasswordException(e: WrongPasswordException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = e.message))
    }
//
//    @ExceptionHandler(InvalidInputException::class)
//    fun InvalidInputException(e: InvalidInputException): ResponseEntity<ErrorResponse> {
//        return ResponseEntity
//            .status(HttpStatus.UNAUTHORIZED)
//            .body(ErrorResponse(e.message))
//    }

}