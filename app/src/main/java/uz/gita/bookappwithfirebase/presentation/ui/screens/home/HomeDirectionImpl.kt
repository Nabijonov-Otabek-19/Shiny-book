package uz.gita.bookappwithfirebase.presentation.ui.screens.home

import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.navigation.AppNavigator
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