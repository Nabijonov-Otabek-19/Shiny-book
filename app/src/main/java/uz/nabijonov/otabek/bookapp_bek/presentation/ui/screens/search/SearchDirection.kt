package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.search

import uz.nabijonov.otabek.bookapp_bek.data.common.BookData

interface SearchDirection {
    suspend fun navigateToAboutScreen(bookData: BookData)
}