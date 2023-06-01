package uz.gita.bookappwithfirebase.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : AppNavigator, NavigationHandler {

    override val navigationBuffer = MutableSharedFlow<NavigationArg>()

    private suspend fun navigate(arg: NavigationArg) {
        navigationBuffer.emit(arg)
    }

    override suspend fun navigateTo(screen: AppScreen) = navigate {
        navigate(screen)
    }

    override suspend fun back() = navigate {
        popBackStack()
    }
}