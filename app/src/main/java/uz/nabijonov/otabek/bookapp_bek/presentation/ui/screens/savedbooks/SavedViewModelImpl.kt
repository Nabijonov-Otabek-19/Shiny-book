package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.savedbooks

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.nabijonov.otabek.bookapp_bek.data.common.BookData
import uz.nabijonov.otabek.bookapp_bek.domain.repository.AppRepositoryImpl
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
        repository.getSavedBookProducts(context).onEach { result ->
            result.onSuccess { booksData.value = it }
            result.onFailure { errorData.value = it.message }
        }.launchIn(viewModelScope)
    }
}