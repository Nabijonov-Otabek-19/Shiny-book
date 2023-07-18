package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.bookread

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookReadViewModelImpl @Inject constructor(
    private val direction: BookReadDirection
) : BookReadViewModel, ViewModel() {

    override fun popBackStack() {
        viewModelScope.launch {
            direction.popBackStack()
        }
    }
}