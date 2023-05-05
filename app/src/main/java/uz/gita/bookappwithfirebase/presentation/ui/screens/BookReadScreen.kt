package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenBookReadBinding

class BookReadScreen : Fragment(R.layout.screen_book_read) {

    private val binding by viewBinding(ScreenBookReadBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}