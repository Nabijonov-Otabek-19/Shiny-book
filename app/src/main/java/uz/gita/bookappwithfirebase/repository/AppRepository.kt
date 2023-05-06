package uz.gita.bookappwithfirebase.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData

interface AppRepository {

    fun getAllBooks(): Flow<Result<List<BookData>>>

    fun downloadBookByUrl(context: Context, book: BookData): Flow<Result<BookData>>

    fun getCategories(): Flow<Result<List<CategoryData>>>

    fun getFavouriteBooks(): Flow<Result<List<BookData>>>

}