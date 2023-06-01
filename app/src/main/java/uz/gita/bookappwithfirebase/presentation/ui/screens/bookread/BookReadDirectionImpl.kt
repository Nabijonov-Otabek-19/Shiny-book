package uz.gita.bookappwithfirebase.presentation.ui.screens.bookread

import uz.gita.bookappwithfirebase.navigation.AppNavigator
import javax.inject.Inject

class BookReadDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): BookReadDirection {

    override suspend fun popBackStack() {
        appNavigator.back()
    }
}