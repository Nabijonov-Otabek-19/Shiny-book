package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
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

    val bookUrlData = MutableLiveData<BookData>()

    init {
        getAllData()
    }

    override fun getAllData() {
        repository.getCategories()
            .onEach { categoryList ->
                categoryList.onSuccess { categoriesData.value = it }
                categoryList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)

        repository.getAllBooks()
            .onEach { bookList ->
                bookList.onSuccess { booksData.value = it }
                bookList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }

    fun downloadBookByUrl(context: Context, book: BookData): BookData {
        var bookDa: BookData? = null
        repository.downloadBookByUrl(context, book)
            .onEach { bookData ->
                bookData.onSuccess { bookUrlData.value = it
                bookDa = it}
                bookData.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)

        return bookDa!!
    }
}