package uz.gita.bookappwithfirebase.presentation.ui.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.data.source.local.SharedPref
import uz.gita.bookappwithfirebase.databinding.ScreenHomeBinding
import uz.gita.bookappwithfirebase.presentation.ui.adapters.HomeAdapter
import uz.gita.bookappwithfirebase.utils.logd
import uz.gita.bookappwithfirebase.utils.toasT
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModelImpl>()

    @Inject
    lateinit var adapter: HomeAdapter

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sharedPref.bookName.isEmpty()) {
            binding.view.visibility = View.GONE
        } else {
            binding.apply {
                binding.view.visibility = View.VISIBLE
                txtBookName.text = sharedPref.bookName
                percentageView.text = "${sharedPref.percentage}%"
            }
        }

        binding.apply {
            btnLastBook.setOnClickListener {
                viewModel.navigateToReadBookScreen(
                    sharedPref.bookName,
                    sharedPref.savedPage,
                    sharedPref.totalPage
                )
            }

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
            logd("HomeScreen Error = $it")
        }
    }
}