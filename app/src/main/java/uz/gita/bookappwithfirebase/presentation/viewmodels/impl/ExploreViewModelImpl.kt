package uz.gita.bookappwithfirebase.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithfirebase.data.common.AllBooksData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.presentation.viewmodels.ExploreViewModel
import uz.gita.bookappwithfirebase.domain.repository.impl.AppRepositoryImpl

class ExploreViewModelImpl : ExploreViewModel, ViewModel() {

    private val repository = AppRepositoryImpl.getInstance()

    override val booksData = MutableLiveData<List<AllBooksData>>()
    override val categoriesData = MutableLiveData<List<CategoryData>>()
    override val errorData = MutableLiveData<String>()

    init {
        getAllData()
    }

    fun getBooksByCategory(categoryNameList: List<String>) {
        val list = ArrayList<AllBooksData>()
        for (index in categoryNameList.indices) {
            repository.getBooksByCategory(categoryNameList[index])
                .onEach { result ->
                    result.onSuccess {
                        list.add(AllBooksData(categoryNameList[index], it))
                        booksData.value = list
                    }
                    result.onFailure { }
                }.launchIn(viewModelScope)
        }
    }


    private fun getAllData() {
        repository.getCategories()
            .onEach { categoryList ->
                categoryList.onSuccess { categoriesData.value = it }
                categoryList.onFailure { errorData.value = it.message }
            }.launchIn(viewModelScope)
    }
}