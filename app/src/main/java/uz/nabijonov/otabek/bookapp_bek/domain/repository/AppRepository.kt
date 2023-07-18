package uz.nabijonov.otabek.bookapp_bek.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.bookapp_bek.data.common.AllBooksData
import uz.nabijonov.otabek.bookapp_bek.data.common.BookData
import uz.nabijonov.otabek.bookapp_bek.data.common.CategoryData

interface AppRepository {

    fun getBookProducts(): Flow<Result<List<AllBooksData>>>

    fun downloadBookByUrl(context: Context, book: BookData): Flow<Result<BookData>>

    fun getSavedBookProducts(context: Context): Flow<Result<List<BookData>>>

    fun getCategories(): Flow<Result<List<CategoryData>>>

    fun getRecommendedBooks(): Flow<Result<List<BookData>>>

    fun getBooksByCategory(category: String): Flow<Result<List<BookData>>>
}