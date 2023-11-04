package com.jesusdmedinac.compose.gobus.data.remote

import com.jesusdmedinac.compose.gobus.data.remote.model.User
import dev.gitlive.firebase.firestore.FirebaseFirestore

interface UserRemoteDataSource {
    suspend fun createUser(user: User): User
    suspend fun getUserByEmail(email: String): User
}

class UserFirebaseDataSource(
    private val fire: FirebaseFirestore,
) : UserRemoteDataSource {
    override suspend fun createUser(user: User): User = fire
        .collection("users")
        .document(user.email)
        .run {
            set(user)
            user
        }

    override suspend fun getUserByEmail(email: String): User =
        fire
            .collection("users")
            .document(email)
            .get()
            .data()
}
