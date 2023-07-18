package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.home

import uz.nabijonov.otabek.bookapp_bek.data.common.BookData
import uz.nabijonov.otabek.bookapp_bek.navigation.AppNavigator
import javax.inject.Inject

class HomeDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeDirection {

    override suspend fun navigateToAboutBookScreen(bookData: BookData) {
        appNavigator.navigateTo(
            HomeScreenDirections.actionHomeScreenToAboutBookScreen(
                bookData
            )
        )
    }
}