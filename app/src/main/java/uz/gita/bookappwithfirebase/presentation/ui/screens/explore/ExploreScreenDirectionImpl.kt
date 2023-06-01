package uz.gita.bookappwithfirebase.presentation.ui.screens.explore

import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.navigation.AppNavigator
import javax.inject.Inject

class ExploreScreenDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ExploreScreenDirection {

    override suspend fun navigateToAboutBookScreen(bookData: BookData) {
        appNavigator.navigateTo(
            ExploreScreenDirections.actionExploreScreenToAboutBookScreen(
                bookData
            )
        )
    }
}