package uz.gita.bookappwithfirebase.presentation.ui.screens.savedbooks

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.BookData

interface SavedViewModel {
    val booksData: LiveData<List<BookData>>
    val loadingData : LiveData<Boolean>
    val errorData: LiveData<String>

    fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int)
}