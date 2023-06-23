package uz.gita.bookappwithfirebase.presentation.ui.screens.search

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
class SearchViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl,
    private val direction: SearchDirection
) : SearchViewModel, ViewModel() {

    override val booksData = MutableLiveData<List<BookData>>()
    override val errorData = MutableLiveData<String>()
    override val loadingData = MutableLiveData<Boolean>()

    init {
        getAllData()
    }

    private fun getAllData() {
        loadingData.value = true
        repository.getRecommendedBooks()
            .onEach { bookData ->
                bookData.onSuccess {
                    loadingData.value = false
                    booksData.value = it
                }
                bookData.onFailure {
                    loadingData.value = false
                    errorData.value = it.message
                }
            }.launchIn(viewModelScope)
    }

    override fun navigateToAboutScreen(bookData: BookData) {
        viewModelScope.launch {
            direction.navigateToAboutScreen(bookData)
        }
    }
}