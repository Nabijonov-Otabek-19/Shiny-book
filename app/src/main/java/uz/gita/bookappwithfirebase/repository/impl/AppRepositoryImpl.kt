package uz.gita.bookappwithfirebase.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.repository.AppRepository

class AppRepositoryImpl : AppRepository {

    override fun getBooks(): Flow<List<BookData>> = callbackFlow {

    }

    override fun getFavouriteBooks(): Flow<List<BookData>> = callbackFlow {

    }
}