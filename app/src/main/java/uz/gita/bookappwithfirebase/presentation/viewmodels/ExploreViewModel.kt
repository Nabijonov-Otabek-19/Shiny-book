package uz.gita.bookappwithfirebase.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.bookappwithfirebase.data.common.AllBooksData
import uz.gita.bookappwithfirebase.data.common.CategoryData

interface ExploreViewModel {

    val booksData: LiveData<List<AllBooksData>>
    val categoriesData: LiveData<List<CategoryData>>
    val errorData: LiveData<String>
}