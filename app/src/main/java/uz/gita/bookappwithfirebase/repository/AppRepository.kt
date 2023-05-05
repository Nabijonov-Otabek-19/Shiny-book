package uz.gita.bookappwithfirebase.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import java.io.File

interface AppRepository {

    fun getCategories(): Flow<Result<List<CategoryData>>>

    fun getBooks(): Flow<Result<List<BookData>>>

    fun getFavouriteBooks(): Flow<Result<List<BookData>>>

}