package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenExploreBinding
import uz.gita.bookappwithfirebase.presentation.ui.adapters.ExploreAdapter
import uz.gita.bookappwithfirebase.presentation.viewmodels.impl.ExploreViewModelImpl

class ExploreScreen : Fragment(R.layout.screen_explore) {

    private val viewBinding by viewBinding(ScreenExploreBinding::bind)
    private val viewModel by viewModels<ExploreViewModelImpl>()
    private val adapter by lazy { ExploreAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setClickListener {
            findNavController().navigate(
                ExploreScreenDirections.actionExploreScreenToAboutBookScreen(
                    it
                )
            )
        }

        val categoryList = ArrayList<String>()
        categoryList.add("Programming")
        categoryList.add("Psychology")
        categoryList.add("Motivation")

        viewModel.getBooksByCategory(categoryList)

        viewModel.booksData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }


        viewModel.categoriesData.observe(viewLifecycleOwner) {

        }

        viewBinding.apply {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
    }
}