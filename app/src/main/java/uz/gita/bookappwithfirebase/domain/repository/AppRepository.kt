package uz.gita.bookappwithfirebase.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappwithfirebase.data.common.AllBooksData
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData

interface AppRepository {

    fun getBookProducts(): Flow<Result<List<AllBooksData>>>

    fun downloadBookByUrl(context: Context, book: BookData): Flow<Result<BookData>>

    fun getSavedBookProducts(context: Context): Flow<Result<List<BookData>>>

    fun getCategories(): Flow<Result<List<CategoryData>>>

    fun getRecommendedBooks(): Flow<Result<List<BookData>>>

    fun getBooksByCategory(category: String): Flow<Result<List<BookData>>>
}