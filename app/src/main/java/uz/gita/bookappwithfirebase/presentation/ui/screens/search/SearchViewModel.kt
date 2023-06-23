package uz.gita.bookappwithfirebase.presentation.ui.screens.search

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.BookData

interface SearchViewModel {

    val booksData: LiveData<List<BookData>>
    val errorData: LiveData<String>
    val loadingData: LiveData<Boolean>

    fun navigateToAboutScreen(bookData: BookData)
}