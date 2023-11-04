package com.jesusdmedinac.compose.gobus.di

import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModelImpl
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignUpScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignUpScreenViewModelImpl
import dev.gitlive.firebase.FirebaseApp
import org.koin.androidx.viewmodel.dsl.viewModel
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

private fun presentationModule() = module {
    viewModel<LoginScreenViewModel> { LoginScreenViewModelImpl() }
    viewModel<SignUpScreenViewModel> { SignUpScreenViewModelImpl(get()) }
}
