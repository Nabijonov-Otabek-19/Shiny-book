package uz.gita.bookappwithfirebase.presentation.ui.screens.search

import uz.gita.bookappwithfirebase.data.common.BookData

interface SearchDirection {
    suspend fun navigateToAboutScreen(bookData: BookData)
}