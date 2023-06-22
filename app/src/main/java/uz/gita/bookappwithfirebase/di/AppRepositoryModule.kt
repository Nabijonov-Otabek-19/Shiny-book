package uz.gita.bookappwithfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bookappwithfirebase.domain.repository.AppRepository
import uz.gita.bookappwithfirebase.domain.repository.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {

    @[Binds Singleton]
    fun bindAuthRepository(impl: AppRepositoryImpl): AppRepository
}