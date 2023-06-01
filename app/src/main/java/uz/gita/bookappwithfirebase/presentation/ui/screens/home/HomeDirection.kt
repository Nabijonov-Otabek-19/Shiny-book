package uz.gita.bookappwithfirebase.presentation.ui.screens.home

import uz.gita.bookappwithfirebase.data.common.BookData

interface HomeDirection {
    suspend fun navigateToAboutScreen(bookData: BookData)
    suspend fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int)
}