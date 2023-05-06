package uz.gita.bookappwithfirebase.repository.impl

import android.content.Context
import android.os.Environment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.repository.AppRepository
import uz.gita.bookappwithfirebase.utils.logd
import java.io.File

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
    private val storage = FirebaseStorage.getInstance()
    private val collectionName = fireStore.collection("books")


    fun getAllBooks(): Flow<Result<List<BookData>>> = callbackFlow {
        collectionName.get()
            .addOnSuccessListener {
                val data = it.toObjects(BookData::class.java)
                trySend(Result.success(data))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    fun downloadBookByUrl(context: Context, book: BookData) = callbackFlow<Result<BookData>> {
        if (File(context.filesDir, book.name).exists()) {
            logd("Book is installed")
            trySend(Result.success(book))
        } else {
            logd("Book is installing...")
            storage.reference.child("books/${book.name}.pdf")
                .getFile(File(context.filesDir, book.name))
                .addOnSuccessListener {
                    trySend(Result.success(book))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
                .addOnProgressListener {
                    val progress = it.bytesTransferred * 100 / it.totalByteCount
                    logd("progress = $progress")
                }
        }
        awaitClose()
    }
        .flowOn(Dispatchers.IO)

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
                logd("Category fail = $it")
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
                logd("books fail = $it")
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getFavouriteBooks(): Flow<Result<List<BookData>>> = callbackFlow {
    }
}