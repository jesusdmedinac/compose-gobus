package com.jesusdmedinac.compose.gobus.domain.repository

import com.jesusdmedinac.compose.gobus.data.remote.AuthRemoteDataSource
import com.jesusdmedinac.compose.gobus.data.remote.UserRemoteDataSource
import com.jesusdmedinac.compose.gobus.domain.mapper.DataUserToDomainUserMapper
import com.jesusdmedinac.compose.gobus.domain.model.User
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException

interface UserRepository {
    suspend fun signUp(user: User): User?
}

class UserRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val dataUserToDomainUserMapper: DataUserToDomainUserMapper,
) : UserRepository {
    override suspend fun signUp(user: User): User? = authRemoteDataSource
        .signUp(user.email, user.password)
        .fold(
            onSuccess = { it },
            onFailure = { throwable ->
                if (throwable is FirebaseAuthUserCollisionException) {
                    authRemoteDataSource.signIn(user.email, user.password)
                } else {
                    null
                }
            },
        )
        ?.let { userRemoteDataSource.createUser(it) }
        ?.let { dataUserToDomainUserMapper.map(it) }
}
