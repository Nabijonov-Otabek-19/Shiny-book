package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenSavedBinding
import uz.gita.bookappwithfirebase.presentation.viewmodels.impl.SavedViewModelImpl
import uz.gita.bookappwithfirebase.utils.logd

class SavedBooksScreen : Fragment(R.layout.screen_saved) {

    private val binding by viewBinding(ScreenSavedBinding::bind)
    private val viewModel by viewModels<SavedViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.booksData.observe(viewLifecycleOwner) {
            logd(it.toString())
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            logd(it)
        }

    }
}