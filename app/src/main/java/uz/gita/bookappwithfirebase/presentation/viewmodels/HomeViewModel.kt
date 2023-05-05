package uz.gita.bookappwithfirebase.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData

interface HomeViewModel {

    val booksData: LiveData<List<BookData>>
    val categoriesData: LiveData<List<CategoryData>>
    val errorData: LiveData<String>

    fun getAllData()
}