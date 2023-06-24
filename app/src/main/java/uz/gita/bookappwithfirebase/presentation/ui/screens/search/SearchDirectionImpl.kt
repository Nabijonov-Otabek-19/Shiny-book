package uz.gita.bookappwithfirebase.presentation.ui.screens.search

import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.navigation.AppNavigator
import javax.inject.Inject

class SearchDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SearchDirection {

    override suspend fun navigateToAboutScreen(bookData: BookData) {
        appNavigator.navigateTo(SearchScreenDirections.actionSearchScreenToAboutBookScreen(bookData))
    }
}