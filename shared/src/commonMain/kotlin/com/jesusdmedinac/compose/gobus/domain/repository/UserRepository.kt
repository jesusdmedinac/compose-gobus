package com.jesusdmedinac.compose.gobus.domain.repository

import com.jesusdmedinac.compose.gobus.data.remote.AuthRemoteDataSource
import com.jesusdmedinac.compose.gobus.data.remote.UserRemoteDataSource
import com.jesusdmedinac.compose.gobus.domain.mapper.DataUserToDomainUserMapper
import com.jesusdmedinac.compose.gobus.domain.mapper.DomainUserToDataUserMapper
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import com.jesusdmedinac.compose.gobus.domain.model.User as DomainUser

interface UserRepository {
    suspend fun signUp(user: DomainUser): DomainUser?
}

class UserRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val dataUserToDomainUserMapper: DataUserToDomainUserMapper,
    private val domainUserToDataUserMapper: DomainUserToDataUserMapper,
) : UserRepository {
    override suspend fun signUp(user: DomainUser): DomainUser? = authRemoteDataSource
        .signUp(user.email, user.password)
        .fold(
            onSuccess = { it?.let { domainUserToDataUserMapper.map(user) } },
            onFailure = { throwable ->
                if (throwable is FirebaseAuthUserCollisionException) {
                    authRemoteDataSource.signIn(user.email, user.password)
                        ?.let { domainUserToDataUserMapper.map(user) }
                } else {
                    null
                }
            },
        )
        ?.let { userRemoteDataSource.createUser(it) }
        ?.let { dataUserToDomainUserMapper.map(it) }
}
