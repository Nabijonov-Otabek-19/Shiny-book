package uz.gita.bookappwithfirebase.presentation.ui.screens.explore

import uz.gita.bookappwithfirebase.data.common.BookData

interface ExploreScreenDirection {
    suspend fun navigateToAboutBookScreen(bookData: BookData)
}