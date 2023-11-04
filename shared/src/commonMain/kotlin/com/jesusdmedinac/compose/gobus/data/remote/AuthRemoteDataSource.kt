package com.jesusdmedinac.compose.gobus.data.remote

import com.jesusdmedinac.compose.gobus.data.remote.model.User
import dev.gitlive.firebase.auth.FirebaseAuth

interface AuthRemoteDataSource {
    suspend fun isUserLogged(): Boolean
    suspend fun signUp(email: String, password: String): Result<User?>

    suspend fun signIn(email: String, password: String): User?
}

class AuthFirebaseDataSource(
    private val auth: FirebaseAuth,
) : AuthRemoteDataSource {
    override suspend fun isUserLogged(): Boolean = auth.currentUser != null
    override suspend fun signUp(email: String, password: String): Result<User?> = runCatching {
        auth
            .createUserWithEmailAndPassword(email, password)
    }
        .fold(
            onSuccess = { authResult ->
                Result.success(
                    authResult.user
                        ?.email
                        ?.let { User(it) },
                )
            },
            onFailure = {
                Result.failure(it)
            },
        )

    override suspend fun signIn(email: String, password: String): User? = auth
        .signInWithEmailAndPassword(email, password)
        .user
        ?.email
        ?.let { User(it) }
}
