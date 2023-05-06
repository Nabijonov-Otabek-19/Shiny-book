package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenAboutBookBinding

class AboutBookScreen : Fragment(R.layout.screen_about_book) {

    private val binding by viewBinding(ScreenAboutBookBinding::bind)
    private val args by navArgs<AboutBookScreenArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookData = args.bookData!!

        binding.apply {
            txtName.text = bookData.name
            txtAuthor.text ="Author: ${bookData.author}"
            txtGenre.text = "Genre: ${bookData.genre}"
            txtPage.text = "Pages: ${bookData.page}"
            txtYear.text = "Year: ${bookData.year}"

            Glide.with(requireContext()).load(bookData.bookCoverUrl).into(imgBook)

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}