package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenBookReadBinding
import uz.gita.bookappwithfirebase.utils.logd


class BookReadScreen : Fragment(R.layout.screen_book_read), OnPageChangeListener,
    OnLoadCompleteListener, OnPageErrorListener {

    private val binding by viewBinding(ScreenBookReadBinding::bind)
    private val args by navArgs<BookReadScreenArgs>()

    private var pageNumber = 0
    private var pdfFileName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookData = args.bookData

        val bookUri = "https://firebasestorage.googleapis.com/v0/b/book-app-8e9d8.appspot.com/o/books%2FThe%20Power%20of%20Habit.pdf?alt=media&token=583b5e49-ca96-4d33-aa07-6685980be430"

        binding.pdfView.fromUri(bookUri.toUri())
            .enableSwipe(true)
            .defaultPage(bookData!!.page.toInt())
            .swipeHorizontal(true)
            .enableDoubletap(true)
            .scrollHandle(null)
            .enableAntialiasing(true)
            .spacing(0)
            .load()
    }

    fun displayFromUri(uri: Uri) {
        pdfFileName = getFileName(uri)

        binding.pdfView.fromUri(uri)
            .defaultPage(pageNumber)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle(DefaultScrollHandle(requireContext()))
            .spacing(10) // in dp
            .onPageError(this)
            .load()
    }

    private fun getFileName(uri: Uri): String {
        val result: String = ""

        if (uri.scheme.equals("content")) {
            val cursor: Cursor
        }

        return result
    }


    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
    }

    override fun loadComplete(nbPages: Int) {
        printBookmarksTree(binding.pdfView.tableOfContents, "-")
    }

    private fun printBookmarksTree(
        tree: List<com.shockwave.pdfium.PdfDocument.Bookmark>,
        sep: String
    ) {
        for (b in tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }

    override fun onPageError(page: Int, t: Throwable?) {
        logd("Cannot load page = $page")
    }
}