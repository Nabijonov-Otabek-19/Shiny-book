package uz.gita.bookappwithfirebase.presentation.ui.screens.search

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData

interface SearchViewModel {

    val booksData: LiveData<List<BookData>>
    val categoriesData : LiveData<List<CategoryData>>
    val errorData: LiveData<String>
    val loadingData: LiveData<Boolean>

    fun getBooksByCategory(category: String)
    fun navigateToAboutScreen(bookData: BookData)
}