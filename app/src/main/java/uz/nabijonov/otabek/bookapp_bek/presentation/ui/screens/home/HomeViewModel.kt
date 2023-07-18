package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.home

import androidx.lifecycle.LiveData
import uz.nabijonov.otabek.bookapp_bek.data.common.AllBooksData
import uz.nabijonov.otabek.bookapp_bek.data.common.BookData

interface HomeViewModel {

    val booksData: LiveData<List<AllBooksData>>
    val errorData: LiveData<String>
    val loadingData : LiveData<Boolean>

    fun navigateToAboutBookScreen(bookData: BookData)

    fun getAllBooks()
}