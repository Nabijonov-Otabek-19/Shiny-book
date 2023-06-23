package uz.gita.bookappwithfirebase.presentation.ui.screens.home

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.BookData

interface HomeViewModel {

    val booksData: LiveData<List<BookData>>
    val errorData: LiveData<String>
    val loadingData: LiveData<Boolean>

    fun navigateToAboutScreen(bookData: BookData)
    fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int)
}