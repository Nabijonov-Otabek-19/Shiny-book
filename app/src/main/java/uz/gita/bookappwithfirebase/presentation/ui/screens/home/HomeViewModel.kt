package uz.gita.bookappwithfirebase.presentation.ui.screens.home

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.AllBooksData
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData

interface HomeViewModel {

    val booksData: LiveData<List<AllBooksData>>
    val categoriesData: LiveData<List<CategoryData>>
    val errorData: LiveData<String>
    val loadingData : LiveData<Boolean>

    fun navigateToAboutBookScreen(bookData: BookData)

    fun getAllBooks()
    fun getAllCategories()
}