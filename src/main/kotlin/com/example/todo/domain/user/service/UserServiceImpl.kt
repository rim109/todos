package com.example.todo.domain.user.service

import com.example.todo.common.exception.InvalidInputException
import com.example.todo.common.auth.JwtTokenProvider
import com.example.todo.common.auth.TokenInfo
import com.example.todo.domain.user.dto.*
import com.example.todo.domain.user.model.UserRole
import com.example.todo.domain.user.model.UserRoleEntity
import com.example.todo.domain.user.model.Users
import com.example.todo.domain.user.repository.UserRepository
import com.example.todo.domain.user.repository.UserRoleRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userRoleRepository: UserRoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
) : UserService {

    //회원가입
    override fun signup(signupRequest: SignupRequest): String {
        var user: Users? = userRepository.findByEmail(signupRequest.email)
        if (user != null) {
            throw InvalidInputException("email", "이미 등록된 email 입니다.")
        }

        user = Users(
            signupRequest.email,
            passwordEncoder.encode(signupRequest.password),
            signupRequest.nickname,
        )

        userRepository.save(user)

        //권한 저장
        val userRole = UserRoleEntity(null, UserRole.MYSELF, user)
        userRoleRepository.save(userRole)

        return "회원가입 완료"
    }


    //로그인
    override fun login(loginRequest: LoginRequest): TokenInfo {
        val userInfo = userRepository.findByEmail(loginRequest.email)
            ?: throw InvalidInputException("password", "등록되지 않은 패스워드입니다.")

        val isValidPassword = passwordEncoder.matches(loginRequest.password, userInfo.password)

        if (!isValidPassword) {
            throw BadCredentialsException("잘못된 패스워드입니다.")
        }
        val authenticationToken = UsernamePasswordAuthenticationToken(userInfo.email, userInfo.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}