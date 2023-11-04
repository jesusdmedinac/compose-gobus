package com.jesusdmedinac.compose.gobus.di

import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModelImpl
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignUpScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignUpScreenViewModelImpl
import dev.gitlive.firebase.FirebaseApp
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(firebaseApp: FirebaseApp) {
    startKoin {
        modules(
            dataModule(firebaseApp),
            domainModule(),
            presentationModule(),
        )
    }
}

object GobusKoinHelper : KoinComponent {
    private val loginScreenViewModel: LoginScreenViewModel by inject()
    private val signUpScreenViewModel: SignUpScreenViewModel by inject()

    fun loginScreenViewModel() = loginScreenViewModel
    fun signUpScreenViewModel() = signUpScreenViewModel
}

private fun presentationModule() = module {
    single<LoginScreenViewModel> { LoginScreenViewModelImpl() }
    single<SignUpScreenViewModel> { SignUpScreenViewModelImpl(get()) }
}
