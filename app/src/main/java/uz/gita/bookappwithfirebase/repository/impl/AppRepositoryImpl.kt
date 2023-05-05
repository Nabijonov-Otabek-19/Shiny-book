package uz.gita.bookappwithfirebase.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.repository.AppRepository

class AppRepositoryImpl : AppRepository {

    companion object {
        private lateinit var repository: AppRepositoryImpl

        fun getInstance(): AppRepositoryImpl {
            if (!(::repository.isInitialized)) {
                repository = AppRepositoryImpl()
            }
            return repository
        }
    }

    private val fireStore = Firebase.firestore

    override fun getCategories(): Flow<Result<List<CategoryData>>> = callbackFlow {
        fireStore.collection("categories").get()
            .addOnSuccessListener { query ->
                val list = ArrayList<CategoryData>()
                query.forEach { categories ->
                    val category = CategoryData(title = categories.get("title").toString())
                    list.add(category)
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getBooks(): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection("books").get()
            .addOnSuccessListener { query ->
                val list = ArrayList<BookData>()
                query.forEach { books ->
                    val book = BookData(
                        author = books.get("author").toString(),
                        name = books.get("name").toString(),
                        bookCoverUrl = books.get("bookCoverUrl").toString(),
                        page = books.get("page").toString(),
                        genre = books.get("genre").toString(),
                        year = books.get("year").toString(),
                        bookUrl = books.get("bookUrl").toString()
                    )
                    list.add(book)
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getFavouriteBooks(): Flow<Result<List<BookData>>> = callbackFlow {

    }
}