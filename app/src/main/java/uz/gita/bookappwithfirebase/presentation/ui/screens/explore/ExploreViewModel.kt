package uz.gita.bookappwithfirebase.presentation.ui.screens.explore

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.AllBooksData
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData

interface ExploreViewModel {

    val booksData: LiveData<List<AllBooksData>>
    val categoriesData: LiveData<List<CategoryData>>
    val errorData: LiveData<String>

    fun navigateToAboutBookScreen(bookData: BookData)
}