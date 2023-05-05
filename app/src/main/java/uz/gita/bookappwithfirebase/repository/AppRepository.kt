package uz.gita.bookappwithfirebase.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappwithfirebase.data.common.BookData

interface AppRepository {

    fun getBooks(): Flow<List<BookData>>

    fun getFavouriteBooks(): Flow<List<BookData>>

}