package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.presentation.viewmodels.SavedViewModel
import uz.gita.bookappwithfirebase.repository.impl.AppRepositoryImpl

class SavedViewModelImpl : SavedViewModel, ViewModel() {

    private val repository = AppRepositoryImpl.getInstance()

    override val booksData = MutableLiveData<List<BookData>>()
    override val errorData = MutableLiveData<String>()

    init {
        getAllData()
    }

    private fun getAllData() {
        repository.getAllBooks()
            .onEach { bookList ->
                bookList.onSuccess { booksData.value = it }
                bookList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}