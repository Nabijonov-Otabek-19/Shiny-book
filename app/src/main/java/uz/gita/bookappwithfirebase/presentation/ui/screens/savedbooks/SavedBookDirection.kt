package uz.gita.bookappwithfirebase.presentation.ui.screens.savedbooks

interface SavedBookDirection {
    suspend fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int)
}