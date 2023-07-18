package uz.nabijonov.otabek.bookapp_bek.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.bookapp_bek.navigation.AppNavigator
import uz.nabijonov.otabek.bookapp_bek.navigation.NavigationDispatcher
import uz.nabijonov.otabek.bookapp_bek.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindsNavigationAppNavigator(navigationDispatcher: NavigationDispatcher): AppNavigator

    @Binds
    fun bindsNavigationHandler(navigationDispatcher: NavigationDispatcher): NavigationHandler
}