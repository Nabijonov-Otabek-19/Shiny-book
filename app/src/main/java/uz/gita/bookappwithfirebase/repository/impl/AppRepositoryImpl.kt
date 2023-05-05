package uz.gita.bookappwithfirebase.repository.impl

import android.os.Environment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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


    fun downloadFiles(): Flow<Result<List<File>>> = callbackFlow {
        val list = ArrayList<File>()
        val rootFile = File(Environment.getExternalStorageDirectory(), "Books")
        if (!rootFile.exists()) rootFile.mkdirs()

        storage.reference.child("books").listAll()
            .addOnSuccessListener { listResult ->
                val fileRefs = listResult.items
                for (file in fileRefs) {
                    logd("Repo file name = ${file.name}")
                    val temp = File(rootFile, file.name)
                    file.getFile(temp)
                        .addOnSuccessListener {
                            list.add(temp)
                            trySend(Result.success(list))
                        }
                        .addOnFailureListener { trySend(Result.failure(it)) }
                        .addOnProgressListener {
                            val progress = it.bytesTransferred * 100 / it.totalByteCount
                            logd("progress = $progress")
                        }
                }
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    // pdfView
    fun downloadFile(): Flow<Result<File>> = callbackFlow {
        val rootFile = File(Environment.getExternalStorageDirectory(), "Demo")
        if (!rootFile.exists()) rootFile.mkdirs()

        val temp = File(rootFile, "missing.pdf")
        storage.reference.child("Missing.pdf")
            .getFile(temp)
            .addOnSuccessListener {
                trySend(Result.success(temp))
            }
            .addOnFailureListener { logd(it.message.toString()) }

            .addOnProgressListener {
                val progress = it.bytesTransferred * 100 / it.totalByteCount
                logd("progress = $progress")
            }
        awaitClose()
    }

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