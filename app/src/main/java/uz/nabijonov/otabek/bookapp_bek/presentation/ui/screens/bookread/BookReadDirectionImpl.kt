package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.bookread

import uz.nabijonov.otabek.bookapp_bek.navigation.AppNavigator
import javax.inject.Inject

class BookReadDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): BookReadDirection {

    override suspend fun popBackStack() {
        appNavigator.back()
    }
}