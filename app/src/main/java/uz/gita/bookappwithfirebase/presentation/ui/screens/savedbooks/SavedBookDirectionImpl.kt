package uz.gita.bookappwithfirebase.presentation.ui.screens.savedbooks

import uz.gita.bookappwithfirebase.navigation.AppNavigator
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