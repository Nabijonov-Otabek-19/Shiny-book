package uz.gita.bookappwithfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bookappwithfirebase.navigation.AppNavigator
import uz.gita.bookappwithfirebase.navigation.NavigationDispatcher
import uz.gita.bookappwithfirebase.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindsNavigationAppNavigator(navigationDispatcher: NavigationDispatcher): AppNavigator

    @Binds
    fun bindsNavigationHandler(navigationDispatcher: NavigationDispatcher): NavigationHandler
}