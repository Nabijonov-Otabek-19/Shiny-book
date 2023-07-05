package uz.gita.bookappwithfirebase.domain.repository

import android.content.Context
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.bookappwithfirebase.data.common.AllBooksData
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.utils.Constants
import uz.gita.bookappwithfirebase.utils.logger
import java.io.File
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {

    private val fireStore = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()


    override fun getBookProducts(): Flow<Result<List<AllBooksData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOKS).get()
            .addOnSuccessListener { query ->

                val list = arrayListOf<AllBooksData>()
                query.forEach { data ->
                    val categoryName = data.get(Constants.CN_CATEGORY).toString()

                    data.reference.collection(Constants.CN_BOOKLIST).get()
                        .addOnSuccessListener { subQuery ->

                            val subList = arrayListOf<BookData>()
                            subQuery.forEach { bookData ->
                                subList.add(bookData.toObject() as BookData)
                            }
                            list.add(AllBooksData(categoryName, subList))
                            trySend(Result.success(list))
                        }
                        .addOnFailureListener { trySend(Result.failure(it)) }
                }
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun downloadBookByUrl(context: Context, book: BookData)
            : Flow<Result<BookData>> = callbackFlow {

        if (File(context.filesDir, book.name).exists()) {
            trySend(Result.success(book))

        } else {
            storage.reference.child("${Constants.SG_FILE_PATH}${book.name}.pdf")
                .getFile(File(context.filesDir, book.name))

                .addOnSuccessListener { trySend(Result.success(book)) }
                .addOnFailureListener { trySend(Result.failure(it)) }

                .addOnProgressListener {
                    val progress = it.bytesTransferred * 100 / it.totalByteCount
                    logger("progress = $progress")
                }
        }
        awaitClose()
    }

    override fun getCategories(): Flow<Result<List<CategoryData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOKS).get()
            .addOnSuccessListener { query ->

                val list = arrayListOf<CategoryData>()
                query.forEach { data ->
                    val categoryName = data.get(Constants.CN_CATEGORY).toString()
                    list.add(CategoryData(categoryName))
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun getRecommendedBooks(): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOKS).get()
            .addOnSuccessListener { query ->

                val list = arrayListOf<BookData>()
                query.forEach { data ->
                    data.reference.collection(Constants.CN_BOOKLIST).get()
                        .addOnSuccessListener { subQuery ->
                            subQuery.forEach { bookData ->
                                list.add(bookData.toObject() as BookData)
                            }
                            trySend(Result.success(list))
                        }
                        .addOnFailureListener { trySend(Result.failure(it)) }
                }
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun getBooksByCategory(category: String): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOKS).get()
            .addOnSuccessListener { query ->

                val list = arrayListOf<BookData>()
                query.forEach { data ->
                    val categoryName = data.get(Constants.CN_CATEGORY).toString()
                    if (category == categoryName) {
                        data.reference.collection(Constants.CN_BOOKLIST).get()
                            .addOnSuccessListener { subQuery ->
                                subQuery.forEach { bookData ->
                                    list.add(bookData.toObject() as BookData)
                                }
                                trySend(Result.success(list))
                            }
                            .addOnFailureListener { trySend(Result.failure(it)) }

                        return@addOnSuccessListener
                    }
                }
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun getSavedBookProducts(context: Context):
            Flow<Result<List<BookData>>> = callbackFlow {

        fireStore.collection(Constants.CN_BOOKS).get()
            .addOnSuccessListener { query ->

                val list = arrayListOf<BookData>()
                query.forEach { data ->
                    data.reference.collection(Constants.CN_BOOKLIST).get()
                        .addOnSuccessListener { subQuery ->
                            subQuery.forEach { bookData ->
                                val book =
                                    File(context.filesDir, bookData.get("name").toString())
                                if (book.exists()) {
                                    val temp = bookData.toObject(BookData::class.java)
                                    list.add(temp)
                                }
                            }
                            trySend(Result.success(list))
                        }
                        .addOnFailureListener { trySend(Result.failure(it)) }
                }
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }
}