package com.example.todo.domain.user.service

import com.example.todo.common.exception.InvalidCredentialException
import com.example.todo.common.exception.ModelNotFoundException
import com.example.todo.domain.user.dto.*
import com.example.todo.domain.user.model.UserRole
import com.example.todo.domain.user.model.Users
import com.example.todo.domain.user.model.toResponse
import com.example.todo.domain.user.repository.UserRepository
import com.example.todo.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {

    //회원가입
    override fun signup(request: SignupRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            Users(
                email = request.email,
                // TODO: 비밀번호 암호화
                password = passwordEncoder.encode(request.password),
                nickname = request.nickname
            )
        ).toResponse()
    }


    //로그인
    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialException()
        }
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email

            )
        )
    }
}