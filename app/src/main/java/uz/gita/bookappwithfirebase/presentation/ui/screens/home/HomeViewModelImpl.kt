package uz.gita.bookappwithfirebase.presentation.ui.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.domain.repository.impl.AppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl,
    private val direction: HomeDirection
) : HomeViewModel, ViewModel() {

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

    override fun navigateToAboutScreen(bookData: BookData) {
        viewModelScope.launch {
            direction.navigateToAboutScreen(bookData)
        }
    }

    override fun navigateToReadBookScreen(bookName: String, savedPage: Int, totalPage: Int) {
        viewModelScope.launch {
            direction.navigateToReadBookScreen(bookName, savedPage, totalPage)
        }
    }
}