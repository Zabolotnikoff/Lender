package com.starchee.lender.di.modules.auth

import com.starchee.lender.di.scopes.AuthScope
import com.starchee.lender.domain.useCases.auth.LoginUseCase
import com.starchee.lender.domain.repositories.AuthRepository
import com.starchee.lender.domain.repositories.LocalRepository
import dagger.Module
import dagger.Provides

@Module
class LoginUseCaseModule {

    @AuthScope
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository, localRepository: LocalRepository) =
        LoginUseCase(authRepository, localRepository)
}