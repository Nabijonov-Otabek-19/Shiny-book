package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.savedbooks

import androidx.lifecycle.LiveData
import uz.nabijonov.otabek.bookapp_bek.data.common.BookData

interface SavedViewModel {
    val booksData: LiveData<List<BookData>>
    val errorData: LiveData<String>

    fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int)
}