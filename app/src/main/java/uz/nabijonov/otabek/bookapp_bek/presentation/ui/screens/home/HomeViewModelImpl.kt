package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.nabijonov.otabek.bookapp_bek.data.common.AllBooksData
import uz.nabijonov.otabek.bookapp_bek.data.common.BookData
import uz.nabijonov.otabek.bookapp_bek.domain.repository.AppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl,
    private val direction: HomeDirection
) : HomeViewModel, ViewModel() {

    override val booksData = MutableLiveData<List<AllBooksData>>()
    override val errorData = MutableLiveData<String>()
    override val loadingData = MutableLiveData<Boolean>()

    override fun navigateToAboutBookScreen(bookData: BookData) {
        viewModelScope.launch {
            direction.navigateToAboutBookScreen(bookData)
        }
    }

    init {
        getAllBooks()
    }

    override fun getAllBooks() {
        loadingData.value = true
        repository.getBookProducts().onEach { result ->
            result.onSuccess {
                loadingData.value = false
                booksData.value = it
            }
            result.onFailure {
                loadingData.value = false
                errorData.value = it.message
            }
        }.launchIn(viewModelScope)
    }
}