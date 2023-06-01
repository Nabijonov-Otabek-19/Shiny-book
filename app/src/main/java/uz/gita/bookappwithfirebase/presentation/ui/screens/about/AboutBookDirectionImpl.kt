package uz.gita.bookappwithfirebase.presentation.ui.screens.about

import uz.gita.bookappwithfirebase.navigation.AppNavigator
import javax.inject.Inject

class AboutBookDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): AboutBookDirection {

    override suspend fun popBackStack() {
        appNavigator.back()
    }
}