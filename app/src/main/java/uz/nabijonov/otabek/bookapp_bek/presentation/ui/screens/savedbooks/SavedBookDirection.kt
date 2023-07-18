package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.savedbooks

interface SavedBookDirection {
    suspend fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int)
}