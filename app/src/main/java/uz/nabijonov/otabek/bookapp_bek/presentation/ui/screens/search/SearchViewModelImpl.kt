package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.nabijonov.otabek.bookapp_bek.data.common.BookData
import uz.nabijonov.otabek.bookapp_bek.data.common.CategoryData
import uz.nabijonov.otabek.bookapp_bek.domain.repository.AppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl,
    private val direction: SearchDirection
) : SearchViewModel, ViewModel() {

    override val booksData = MutableLiveData<List<BookData>>()
    override val categoriesData = MutableLiveData<List<CategoryData>>()
    override val errorData = MutableLiveData<String>()
    override val loadingData = MutableLiveData<Boolean>()

    override fun getBooksByCategory(category: String) {
        loadingData.value = true
        repository.getBooksByCategory(category).onEach { bookData ->
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

        repository.getCategories().onEach { result ->
            result.onSuccess { categoriesData.value = it }
            result.onFailure { errorData.value = it.message }
        }.launchIn(viewModelScope)
    }

    override fun navigateToAboutScreen(bookData: BookData) {
        viewModelScope.launch {
            direction.navigateToAboutScreen(bookData)
        }
    }
}