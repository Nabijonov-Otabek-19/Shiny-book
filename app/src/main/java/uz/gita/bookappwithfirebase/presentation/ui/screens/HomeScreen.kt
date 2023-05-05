package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenHomeBinding
import uz.gita.bookappwithfirebase.presentation.ui.adapters.HomeAdapter
import uz.gita.bookappwithfirebase.presentation.viewmodels.impl.HomeViewModelImpl

class HomeScreen : Fragment(R.layout.screen_home) {

    private val viewBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy { HomeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.booksData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.categoriesData.observe(viewLifecycleOwner) {

        }

        viewBinding.apply {
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = adapter
        }
    }
}