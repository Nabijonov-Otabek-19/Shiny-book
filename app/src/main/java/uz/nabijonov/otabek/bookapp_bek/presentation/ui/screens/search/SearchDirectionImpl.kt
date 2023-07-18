package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.search

import uz.nabijonov.otabek.bookapp_bek.data.common.BookData
import uz.nabijonov.otabek.bookapp_bek.navigation.AppNavigator
import javax.inject.Inject

class SearchDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SearchDirection {

    override suspend fun navigateToAboutScreen(bookData: BookData) {
        appNavigator.navigateTo(SearchScreenDirections.actionSearchScreenToAboutBookScreen(bookData))
    }
}