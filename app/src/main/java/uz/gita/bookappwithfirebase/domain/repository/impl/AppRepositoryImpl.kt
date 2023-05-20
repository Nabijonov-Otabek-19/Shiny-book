package uz.gita.bookappwithfirebase.domain.repository.impl

import android.content.Context
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.domain.repository.AppRepository
import uz.gita.bookappwithfirebase.utils.Constants
import uz.gita.bookappwithfirebase.utils.logd
import java.io.File
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor() : AppRepository {

    private val fireStore = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()


    override fun getAllBooks(): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOK_NAME).get()
            .addOnSuccessListener {
                val data = it.toObjects(BookData::class.java)
                trySend(Result.success(data))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    fun getBooksByCategory(categoryName: String): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOK_NAME)
            .whereEqualTo("genre", categoryName).get()
            .addOnSuccessListener {
                val data = it.toObjects(BookData::class.java)
                trySend(Result.success(data))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
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
                    logd("progress = $progress")
                }
        }
        awaitClose()
    }

    override fun getCategories(): Flow<Result<List<CategoryData>>> = callbackFlow {
        fireStore.collection(Constants.CN_CATEGORY_NAME).get()
            .addOnSuccessListener {
                val data = it.toObjects(CategoryData::class.java)
                trySend(Result.success(data))
            }
            .addOnFailureListener {
                logd("Category fail = $it")
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getRecommendedBooks(): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOK_NAME).get()
            .addOnSuccessListener {
                val list = ArrayList<QueryDocumentSnapshot>()

                it.forEach { document -> list.add(document) }
                val sizeOfCollection = it.size()

                val randomDocuments = mutableListOf<BookData>()
                val uniqueRandomIndexes = mutableSetOf<Int>()
                val size = 3 // Random olmoqchi bo'lgan data lar soni

                while (uniqueRandomIndexes.size < size) { // sikl bo'ylab random sonlarni olish
                    uniqueRandomIndexes.add((0 until sizeOfCollection).random())
                }

                uniqueRandomIndexes.forEach { randomIndex ->
                    val data = list[randomIndex].toObject(BookData::class.java)
                    randomDocuments.add(data)
                }
                trySend(Result.success(randomDocuments))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getSavedBooks(context: Context): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOK_NAME).get()
            .addOnSuccessListener {
                val list = ArrayList<BookData>()
                it.forEach { data ->
                    val book = File(context.filesDir, data.get("name").toString())
                    if (book.exists()) {
                        val temp = data.toObject(BookData::class.java)
                        list.add(temp)
                    }
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }
}