package uz.gita.bookappwithfirebase.repository.impl

import android.content.Context
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.repository.AppRepository
import uz.gita.bookappwithfirebase.utils.Constants
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

    override fun getFavouriteBooks(): Flow<Result<List<BookData>>> = callbackFlow {}

    override fun getRecommendedBooks(): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection(Constants.CN_BOOK_NAME).limit(2).get()
            .addOnSuccessListener {
                val data = it.toObjects(BookData::class.java)
                trySend(Result.success(data))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }
}