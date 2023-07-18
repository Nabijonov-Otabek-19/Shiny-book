package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.savedbooks

import uz.nabijonov.otabek.bookapp_bek.navigation.AppNavigator
import javax.inject.Inject

class SavedBookDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SavedBookDirection {

    override suspend fun navigateToReadBookScreen(
        bookName: String,
        savedPage: Int,
        totalPage: Int
    ) {
        appNavigator.navigateTo(
            SavedBooksScreenDirections.actionSavedBooksScreenToBookReadScreen(
                bookName, savedPage, totalPage
            )
        )
    }
}