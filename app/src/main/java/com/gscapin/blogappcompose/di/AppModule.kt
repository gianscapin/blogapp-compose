package com.gscapin.blogappcompose.di

import com.gscapin.blogappcompose.data.remote.auth.AuthDataSource
import com.gscapin.blogappcompose.domain.auth.AuthRepo
import com.gscapin.blogappcompose.domain.auth.AuthRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    fun provideAuthRepository(dataSource: AuthDataSource) = AuthRepoImpl(dataSource)
}