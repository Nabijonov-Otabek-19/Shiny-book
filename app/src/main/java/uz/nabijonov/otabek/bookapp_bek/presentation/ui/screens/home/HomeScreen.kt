package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.nabijonov.otabek.bookapp_bek.R
import uz.nabijonov.otabek.bookapp_bek.databinding.ScreenHomeBinding
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.adapters.HomeAdapter
import uz.nabijonov.otabek.bookapp_bek.utils.logger
import uz.nabijonov.otabek.bookapp_bek.utils.toasT
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val viewBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModelImpl>()

    @Inject
    lateinit var adapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setClickListener {
            viewModel.navigateToAboutBookScreen(it)
        }

        viewModel.booksData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            toasT(it)
            logger("Explore screen error = $it")
        }

        viewModel.loadingData.observe(viewLifecycleOwner) {
            val isLoad = if (it) View.VISIBLE else View.GONE
            viewBinding.progressBar.visibility = isLoad
        }

        viewBinding.apply {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
    }
}