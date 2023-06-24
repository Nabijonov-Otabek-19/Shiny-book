package uz.gita.bookappwithfirebase.presentation.ui.screens.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenSearchBinding
import uz.gita.bookappwithfirebase.presentation.ui.adapters.SearchBookAdapter
import uz.gita.bookappwithfirebase.presentation.ui.adapters.SearchCategoryAdapter
import uz.gita.bookappwithfirebase.utils.logger
import uz.gita.bookappwithfirebase.utils.toasT
import javax.inject.Inject

@AndroidEntryPoint
class SearchScreen : Fragment(R.layout.screen_search) {

    private val binding by viewBinding(ScreenSearchBinding::bind)
    private val viewModel by viewModels<SearchViewModelImpl>()

    @Inject
    lateinit var categoryAdapter: SearchCategoryAdapter

    @Inject
    lateinit var bookAdapter: SearchBookAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerCategory.adapter = categoryAdapter

            recyclerBooks.layoutManager = LinearLayoutManager(requireContext())
            recyclerBooks.adapter = bookAdapter
        }

        categoryAdapter.setClickListener { category ->
            viewModel.getBooksByCategory(category)
        }

        bookAdapter.setClickListener {
            viewModel.navigateToAboutScreen(it)
        }

        viewModel.categoriesData.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it)
        }

        viewModel.booksData.observe(viewLifecycleOwner) {
            bookAdapter.setData(it.shuffled())
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            toasT(it)
            logger("HomeScreen Error = $it")
        }

        viewModel.loadingData.observe(viewLifecycleOwner) {
            val isLoad = if (it) View.VISIBLE else View.GONE
            binding.progressBar.visibility = isLoad
        }
    }
}