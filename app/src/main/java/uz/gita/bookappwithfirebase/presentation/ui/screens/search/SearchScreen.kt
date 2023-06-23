package uz.gita.bookappwithfirebase.presentation.ui.screens.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenHomeBinding
import uz.gita.bookappwithfirebase.presentation.ui.adapters.HomeAdapter
import uz.gita.bookappwithfirebase.utils.logger
import uz.gita.bookappwithfirebase.utils.toasT
import javax.inject.Inject

@AndroidEntryPoint
class SearchScreen : Fragment(R.layout.screen_search) {

    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel by viewModels<SearchViewModelImpl>()

    @Inject
    lateinit var adapter: HomeAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recycler.adapter = adapter
        }

        adapter.setClickListener {
            viewModel.navigateToAboutScreen(it)
        }

        viewModel.booksData.observe(viewLifecycleOwner) {
            adapter.setData(it)
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