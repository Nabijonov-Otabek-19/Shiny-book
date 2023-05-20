package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.presentation.viewmodels.SavedViewModel
import uz.gita.bookappwithfirebase.domain.repository.impl.AppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class SavedViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl
) : SavedViewModel, ViewModel() {

    override val booksData = MutableLiveData<List<BookData>>()
    override val errorData = MutableLiveData<String>()


    fun getAllData(context: Context) {
        repository.getSavedBooks(context)
            .onEach { bookList ->
                bookList.onSuccess { booksData.value = it }
                bookList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}