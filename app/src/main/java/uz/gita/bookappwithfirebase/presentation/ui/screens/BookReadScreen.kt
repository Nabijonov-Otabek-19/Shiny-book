package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenBookReadBinding
import uz.gita.bookappwithfirebase.utils.logd
import java.io.File

class BookReadScreen : Fragment(R.layout.screen_book_read), OnPageChangeListener,
    OnLoadCompleteListener, OnPageErrorListener {

    private val binding by viewBinding(ScreenBookReadBinding::bind)
    private val args by navArgs<BookReadScreenArgs>()

    private var pageNumber = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookData = args.bookData

        val file = File(requireContext().filesDir, bookData!!.name)

        binding.pdfView.fromFile(file)
            .enableSwipe(true)
            .defaultPage(0)
            .swipeHorizontal(true)
            .enableDoubletap(true)
            .scrollHandle(null)
            .enableAntialiasing(true)
            .spacing(0)
            .load()
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