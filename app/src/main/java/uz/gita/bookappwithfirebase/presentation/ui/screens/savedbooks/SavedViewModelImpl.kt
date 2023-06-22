package uz.gita.bookappwithfirebase.presentation.ui.screens.savedbooks

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.domain.repository.AppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class SavedViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl,
    private val direction: SavedBookDirection
) : SavedViewModel, ViewModel() {

    override val booksData = MutableLiveData<List<BookData>>()
    override val errorData = MutableLiveData<String>()

    override fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int) {
        viewModelScope.launch {
            direction.navigateToReadBookScreen(bookName, savedPage, totalPage)
        }
    }


    fun getAllData(context: Context) {
        repository.getSavedBooks(context)
            .onEach { bookList ->
                bookList.onSuccess { booksData.value = it }
                bookList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}