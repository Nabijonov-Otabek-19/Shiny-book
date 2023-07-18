package uz.nabijonov.otabek.bookapp_bek.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.bookapp_bek.domain.repository.AppRepository
import uz.nabijonov.otabek.bookapp_bek.domain.repository.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {

    @[Binds Singleton]
    fun bindAuthRepository(impl: AppRepositoryImpl): AppRepository
}