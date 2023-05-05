package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.presentation.viewmodels.HomeViewModel
import uz.gita.bookappwithfirebase.repository.impl.AppRepositoryImpl

class HomeViewModelImpl : HomeViewModel, ViewModel() {

    private val repository = AppRepositoryImpl.getInstance()

    override val booksData = MutableLiveData<List<BookData>>()
    override val categoriesData = MutableLiveData<List<CategoryData>>()
    override val errorData = MutableLiveData<String>()

    init {
        getAllData()
    }

    override fun getAllData() {
        repository.getCategories()
            .onEach { categoryList ->
                categoryList.onSuccess { categoriesData.value = it }
                categoryList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)

        repository.getBooks()
            .onEach { bookList ->
                bookList.onSuccess { booksData.value = it }
                bookList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}