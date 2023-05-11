package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenAboutBookBinding
import uz.gita.bookappwithfirebase.domain.repository.impl.AppRepositoryImpl
import uz.gita.bookappwithfirebase.utils.logd
import uz.gita.bookappwithfirebase.utils.toasT
import java.io.File

class AboutBookScreen : Fragment(R.layout.screen_about_book) {

    private val binding by viewBinding(ScreenAboutBookBinding::bind)
    private val args by navArgs<AboutBookScreenArgs>()

    private val repository = AppRepositoryImpl.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookData = args.bookData!!

        val book = File(requireContext().filesDir, bookData.name)

        binding.apply {
            txtName.text = bookData.name
            txtAuthor.text = "Author: ${bookData.author}"
            txtGenre.text = "Genre: ${bookData.genre}"
            txtPage.text = "Pages: ${bookData.page}"
            txtYear.text = "Year: ${bookData.year}"

            Glide.with(requireContext()).load(bookData.bookCoverUrl).into(imgBook)

            if (book.exists()) {
                btnDownload.setImageResource(R.drawable.ic_saved)
                btnDownload.isClickable = false
            } else {
                btnDownload.setImageResource(R.drawable.download)
                btnDownload.isClickable = true
            }

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnDownload.setOnClickListener {
                repository.downloadBookByUrl(requireContext(), bookData)
                    .onEach { data ->
                        data.onSuccess {
                            toasT("Book saved")
                        }
                        data.onFailure { e ->
                            logd("AboutBookScreen error = ${e.message}")
                        }
                    }.launchIn(lifecycleScope)
                btnDownload.setImageResource(R.drawable.ic_saved)
                it.isClickable = false
            }
        }
    }
}