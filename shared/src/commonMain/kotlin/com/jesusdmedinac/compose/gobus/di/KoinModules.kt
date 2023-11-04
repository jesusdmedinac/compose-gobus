package com.jesusdmedinac.compose.gobus.di

import com.jesusdmedinac.compose.gobus.data.remote.AuthFirebaseDataSource
import com.jesusdmedinac.compose.gobus.data.remote.AuthRemoteDataSource
import com.jesusdmedinac.compose.gobus.data.remote.UserFirebaseDataSource
import com.jesusdmedinac.compose.gobus.data.remote.UserRemoteDataSource
import com.jesusdmedinac.compose.gobus.domain.mapper.DataUserToDomainUserMapper
import com.jesusdmedinac.compose.gobus.domain.repository.UserRepository
import com.jesusdmedinac.compose.gobus.domain.repository.UserRepositoryImpl
import com.jesusdmedinac.compose.gobus.domain.usecase.SignUpUseCase
import com.jesusdmedinac.compose.gobus.domain.usecase.SignUpUseCaseImpl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

fun dataModule(firebaseApp: FirebaseApp) = module {
    single { Firebase.auth(firebaseApp) }
    single { Firebase.firestore(firebaseApp) }
    single<UserRemoteDataSource> { UserFirebaseDataSource(get()) }
    single<AuthRemoteDataSource> { AuthFirebaseDataSource(get()) }
}

fun domainModule() = module {
    single { DataUserToDomainUserMapper() }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single<SignUpUseCase> { SignUpUseCaseImpl(get()) }
}
