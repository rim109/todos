package com.example.todo.detail.service

import com.example.todo.common.dto.CustomUser
import com.example.todo.domain.user.model.Users
import com.example.todo.domain.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails =
        userRepository.findByEmail(email)
            ?.let { createUserDetails(it) }
            ?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")

    private fun createUserDetails(users: Users): UserDetails =
        CustomUser(
            users.id!!,
            users.nickname,
            passwordEncoder.encode(users.password),
            users.userRole!!.map { SimpleGrantedAuthority("ROLE_${it.role}") }
        )
}
