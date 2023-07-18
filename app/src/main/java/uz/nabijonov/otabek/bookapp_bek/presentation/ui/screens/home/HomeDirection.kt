package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.home

import uz.nabijonov.otabek.bookapp_bek.data.common.BookData

interface HomeDirection {
    suspend fun navigateToAboutBookScreen(bookData: BookData)
}