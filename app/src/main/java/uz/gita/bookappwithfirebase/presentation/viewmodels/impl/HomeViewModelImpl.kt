package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.presentation.viewmodels.HomeViewModel
import uz.gita.bookappwithfirebase.domain.repository.impl.AppRepositoryImpl

class HomeViewModelImpl : HomeViewModel, ViewModel() {

    private val repository = AppRepositoryImpl.getInstance()

    override val booksData = MutableLiveData<List<BookData>>()
    override val errorData = MutableLiveData<String>()

    init {
        getAllData()
    }

    private fun getAllData() {
        repository.getRecommendedBooks()
            .onEach { bookData ->
                bookData.onSuccess { booksData.value = it }
                bookData.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}