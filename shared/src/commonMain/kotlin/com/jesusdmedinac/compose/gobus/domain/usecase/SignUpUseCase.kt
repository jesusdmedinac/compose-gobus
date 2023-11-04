package com.jesusdmedinac.compose.gobus.domain.usecase

import com.jesusdmedinac.compose.gobus.domain.model.User
import com.jesusdmedinac.compose.gobus.domain.repository.UserRepository

interface SignUpUseCase {
    suspend operator fun invoke(user: User): User?
}

class SignUpUseCaseImpl(
    private val userRepository: UserRepository,
) : SignUpUseCase {
    override suspend fun invoke(user: User): User? = userRepository.signUp(user)
}
