package com.asetec.data.supabase

import com.asetec.data.supabase.repository.AuthenticationRepositoryImpl
import com.asetec.domain.repository.user.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {
    @Binds
    abstract fun bindAuthenticationRepository(
        impl: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}