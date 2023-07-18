package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.about

import uz.nabijonov.otabek.bookapp_bek.navigation.AppNavigator
import javax.inject.Inject

class AboutBookDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): AboutBookDirection {

    override suspend fun popBackStack() {
        appNavigator.back()
    }
}